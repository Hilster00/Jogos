import sys
import random
import JogovelhaMinMax as jogovelha
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QPainter, QPen
from PyQt5.QtWidgets import QApplication, QWidget

q_linhas = 3
q_colunas = 3
tamanho=90

tabuleiro = [
[None,None,None],
[None,None,None],
[None,None,None],
]

class Jogo_Velha(QWidget):
    def __init__(self, parent=None):

        #criação da janela
        super().__init__(parent)
        self.setWindowTitle("Jogo da Velha")
        self.setFixedSize(q_colunas * tamanho, q_linhas * tamanho)

        #sorteio do símbolo do jogador
        self.__jogador="X" if random.randint(0,1) == 1 else "O"
        self.__bot="X" if self.__jogador == "O" else "O"

        #sorteia se o bot começa ou não
        if random.randint(0,1) == 1:
            jogovelha.jogada_maquina(tabuleiro, self.__bot)

        #destrava para o player poder jogar  
        self.__trava=True
        
        
    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton and self.__trava:
            # Obtém as coordenadas do clique
            x = event.x() // tamanho
            y = event.y() // tamanho
            if tabuleiro[y][x] == None:
                
                tabuleiro[y][x]=self.__jogador
                self.__trava=False
                self.update()
                for i in range(2):
                    if jogovelha.verificar_vitoria(tabuleiro, "X"):
                        print("'X' venceu")
                        break
                    elif jogovelha.verificar_vitoria(tabuleiro, "O"):
                        print("'O' venceu")
                        break
                    elif jogovelha.verificar_empate(tabuleiro):
                        print("Empate")
                        break
                    elif i == 0:
                        jogovelha.jogada_maquina(tabuleiro, self.__bot)
                else:
                    self.__trava=True
                self.update()

            
                
    def vitoria(self):
        painter = QPainter(self)
        pen = QPen(Qt.black, 2, Qt.SolidLine)
        painter.setPen(pen)

        #desenha o risco caso tenha ganhado na horizontal
        for i,linha in enumerate(tabuleiro):
            linha=list(set(linha))
            if len(linha) == 1 and linha[0] != None:
                painter.drawLine(0, 45, self.width(), 45)
                
        #desenha o risco caso tenha ganhado na vertical
        for j in range(3):
            linha=list(set([tabuleiro[i][j] for i in range(3)]))
            if len(linha) == 1 and linha[0] != None:
                painter.drawLine(45, 0, 45, self.height())


        #desenha o risco caso tenha ganhado na diagonal
        linha=list(set([tabuleiro[i][i] for i in range(3)]))
        if len(linha) == 1 and linha[0] != None:
            painter.drawLine(0, 0, self.width(), self.height())
            
        linha=list(set([tabuleiro[i][2-i] for i in range(3)]))        
        if len(linha) == 1 and linha[0] != None:
            painter.drawLine(self.width(), 0, 0, self.height())

        self.update()


        
    def paintEvent(self, event):
        painter = QPainter(self)
        pen = QPen(Qt.black, 2, Qt.SolidLine)
        painter.setPen(pen)
        # Desenha linhas verticais
        for i in range(q_colunas+1):
            x = i * self.width() // q_colunas
            painter.drawLine(x, 0, x, self.height())

        # Desenha linhas horizontais
        for i in range(q_linhas+1):
            y = i * self.height() // q_linhas
            painter.drawLine(0, y, self.width(), y)

        for i,linha in enumerate(tabuleiro):
            for j,casa in enumerate(linha):
                if casa != None:
                    if casa == "X":
                        painter.drawLine(j*tamanho, i*tamanho, (j+1)*tamanho, (i+1)*tamanho)
                        painter.drawLine((j+1)*tamanho, i*tamanho, j*tamanho, (i+1)*tamanho)
                    else:
                        painter.drawEllipse(j*tamanho, i*tamanho, tamanho, tamanho)

        if jogovelha.verificar_vitoria(tabuleiro, "X"):
            self.vitoria()
        elif jogovelha.verificar_vitoria(tabuleiro, "O"):
            self.vitoria()



               
if __name__ == '__main__':
    app = QApplication(sys.argv)
    jogo_velha = Jogo_Velha()
    jogo_velha.show()
    sys.exit(app.exec_())
