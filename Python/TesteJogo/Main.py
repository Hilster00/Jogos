import sys
import math
from PyQt5.QtCore import Qt, QTimer
from PyQt5.QtGui import QPixmap, QTransform
from PyQt5.QtWidgets import QApplication, QWidget, QLabel

limites={"superior":0,"inferior":300,
"esquerdo":0,"direito":550}


mapa=[
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
]


class JanelaJogo(QWidget):

    def __init__(self, parent=None):
        super().__init__(parent)
        self.setWindowTitle("Jogo")
        self.setFixedSize(600, 350)

        # Define a velocidade inicial e a gravidade
        self.__velocidade_y = 0
        self.__gravidade = 1
        self.gravidade_ativa=True

        # Carrega a imagem usando QPixmap
        imagem = QPixmap('madeira.png')  # Substitua com o caminho da sua imagem

        # Cenário
        self.__mapa = []
        for i in range(0, 351, 50):
        
            linha = [QLabel(self) for j in range(0, 601, 50)]
            for j, _ in enumerate(linha):
                if j != 3:
                    linha[j].setPixmap(imagem)
                    linha[j].setVisible(j % 2 == (i / 50) % 2)
                    linha[j].setGeometry(j * 50, i, 50, 50)
                    linha[j].setScaledContents(True)
                    
            self.__mapa.append(linha)

        # Jogador
        self._imagem2 = QPixmap("seta.png")
        self.__player = QLabel(self)
        self.__player.setPixmap(self._imagem2)
        self.__player.setGeometry(300, 300, 50, 50)
        self.__player.setScaledContents(True)
        self.pos_x = 300
        self.pos_y = 150

        

        #evento gravidade
        self._Gravidade = QTimer(self)
        self._Gravidade.timeout.connect(self.funcao_gravidade)
        self._Gravidade.start(33)

        # Eventos da cabeça
        self._MouseEvent = QTimer(self)
        self._MouseEvent.timeout.connect(self.cabeca_player)
        #self._MouseEvent.timeout.connect(self.funcao_continua)
        self._MouseEvent.start(33)
        

        # Mensagem a ser exibida
        self.__mensagem = ""

    #funções que vão rodar em loop

    def atualizar_mapa(self):
        for i,linha in enumerate(mapa):
            for j,bloco in enumerate(linha):
                ...
                
    def funcao_gravidade(self):
        if self.gravidade_ativa == True:
            if self.pos_y<300:
                self.pos_y += int(self.__velocidade_y*1.6)
                self.__velocidade_y += self.__gravidade

                # Limita a posição do jogador ao chão
                if self.pos_y >= 300:  # Altura do chão (ajuste conforme necessário)
                    self.pos_y = 300
                    self.__velocidade_y = 0  # Define a velocidade como zero quando o jogador toca o chão
                    
                    
            if self.__velocidade_y==0 and self.pos_y<300:
                self.__velocidade_y=1
        else:
            self.pos_y=self.pos_y//50
            self.pos_y*=50
        # Atualiza a posição do jogador na janela
        self.__player.setGeometry(self.pos_x, self.pos_y, 50, 50)
        
    def cabeca_player(self):
        
        #posicao mouse e cabeca_player
        mouse_pos = self.mapFromGlobal(self.cursor().pos())
        delta_x = mouse_pos.x() - self.__player.x()
        delta_y = mouse_pos.y() - self.__player.y()

        #angulo entre cabeca e mouse
        angulo = int(math.degrees(math.atan2(delta_y, delta_x)))

        # Rotacionar a imagem do jogador
        transform = QTransform().rotate(angulo)
        imagem_rotacionada = self._imagem2.transformed(transform)

        # Definir a imagem rotacionada como pixmap do jogador
        self.__player.setPixmap(imagem_rotacionada)

        angulo%=90
        angulo=angulo if angulo <= 45 else 45-angulo%45
        
       
        distancia=int(angulo/45*20)
    
        self.__player.setGeometry(self.pos_x-distancia//2, self.pos_y-distancia//2, 50+distancia, 50+distancia)
        

    
    def funcao_continua(self):
        #if self.__mensagem != "":
            #print(self.__mensagem)
            #self.__mensagem = ""

        #antigo local de rotacionar a cabeça jogador
       ...
        
     


    #funções de eventos de input
        
    def mousePressEvent(self, event):
        if self.__mensagem=="":
            self.__mensagem="Botão do mouse pressionado!"
            #print(event.button())
        if event.button() == 4:
            self.gravidade_ativa=not(self.gravidade_ativa)
            print("Gravidade Ligada" if self.gravidade_ativa else "Gravidade Desligada")

        elif event.button() == 16:
            self.pos_y -= 50
            self.gravidade_ativa=False
            
        elif event.button() == 8:
            self.pos_y += 50
            if self.pos_y>=300:
                self.pos_y=300
                self.gravidade_ativa=True
            

            
    def mouseReleaseEvent(self, event):
        if self.__mensagem=="":
            self.__mensagem="Botão do mouse solto!"

    def mouseDoubleClickEvent(self, event):
        self.__mensagem="Clique duplo do mouse!"

    def mouseMoveEvent(self, event):
        self.__mensagem=f"Mouse movido para: X={event.x()}, Y={event.y()}"

    def wheelEvent(self, event):
        angle_delta = event.angleDelta().y() / 8
        self.__mensagem=f"Roda do mouse girada: Delta={angle_delta}"

    def atualizar_posicao_mouse(self):
        cursor_pos = self.mapFromGlobal(self.cursor().pos())
        x = cursor_pos.x()
        y = cursor_pos.y()
        return x, y

    def keyPressEvent(self, event):
        key = event.key()
        if key == Qt.Key_Up or key == Qt.Key_W:
            self.pos_y -= 50
            if limites["superior"]>self.pos_y:
                self.pos_y = limites["superior"]
        elif key == Qt.Key_Down or key == Qt.Key_S:
            self.pos_y += 50
            if limites["inferior"]<self.pos_y:
                self.pos_y = limites["inferior"]
        elif key == Qt.Key_Left or key == Qt.Key_A:
            self.pos_x -= 50
            if limites["esquerdo"]>self.pos_x:
                self.pos_x = limites["esquerdo"]
        elif key == Qt.Key_Right or key == Qt.Key_D:
            self.pos_x += 50
            if limites["direito"]<self.pos_x:
                self.pos_x = limites["direito"]
        self.__player.setGeometry(self.pos_x, self.pos_y, 50, 50)
  

    #funções de fórmulas

    @staticmethod
    def calcular_angulo(x1,y1,x2,y2):
        delta_x = x2 - x1
        delta_y = y2 - y1
        angulo_radianos = math.atan2(delta_y, delta_x)
        return int(math.degrees(angulo_radianos))


if __name__ == "__main__":
    app = QApplication(sys.argv)
    jogo = JanelaJogo()
    jogo.show()
    sys.exit(app.exec_())
