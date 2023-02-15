class jogo_velha:
    
    def __init__(self,primeiro_jogador="X"):
        self.__vez=primeiro_jogador
        #matriz do tabuleiro 
        self.__tabuleiro=[[None for i in range(3)] for i in range(3)]
        self.__casas_disponiveis=list(range(1,10))
        self.vencedor=None
    
    @property
    def casas_disponiveis(self):
        return self.__casas_disponiveis

    @property
    def vez(self):
        return self.__vez
            
    def tabuleiro(self,y=None,x=None):
        # se y == None, logo nenhum parametro foi passado
        if y == None:
            #caso não especifique uma casa
            return self.__tabuleiro
        elif  x == None:
            y-=1
            #retorna a linha especificada
            return self.__tabuleiro[y]
        #caso especifique uma casa
        return self.__tabuleiro[y-1][x-1]
    
    def __verifica_vencedor(self):
        
        #verifica se os elementos da linha são iguais
        for i in range(3):
            jogador=self.__tabuleiro[i][0]
            if jogador == None:
                continue
            for j in range(1,3):
                if self.__tabuleiro[i][j] != jogador:
                    break
            else:
                self.vencedor=jogador
                return jogador
        
        #verifica se os elementos da colunas são iguais
        for j in range(3):
            jogador=self.__tabuleiro[0][j]
            if jogador == None:
                continue
            for i in range(1,3):
                if self.__tabuleiro[i][j] != jogador:
                    break
            else:
                self.vencedor=jogador
                return jogador
        
        #diagonal principal
        jogador=self.__tabuleiro[0][0]
        if jogador != None:
            for i in range(1,3):
                if self.__tabuleiro[i][i] != jogador:
                    break
            else:
                self.vencedor=jogador
                return jogador
                
        #diagonal principal
        jogador=self.__tabuleiro[0][2]
        if jogador != None:
            for i in range(1,3):
                if self.__tabuleiro[i][2-i] != jogador:
                    break
            else:
                self.vencedor=jogador
                return jogador
                    
        return None
        
    def __fazer_jogada(self,y,x):
        if self.__tabuleiro[y][x] == None:
            self.__tabuleiro[y][x]=self.vez
            self.vencedor=self.__verifica_vencedor()
            self.__vez='X' if self.__vez == 'O' else 'O'
            return self.vencedor
        else:
            return "Casa Ocupada"
        
    def jogada(self,y=None,x=None):
        mensagem=None
        erro=ValueError
        
        if y == None or x == None:
            if x==y:
                mensagem ="X,Y não especificados"
            else:
                m='Y' if y == None else "X"
                mensagem =f"{m} não especificado"
            
            
        elif type(x) != int or type(y) != int:
            erro=TypeError
            if type(x)!=int and type(y) != int:
                mensagem="X,Y não são do tipo inteiro"
            else:
                m='Y' if type(y)!=int else "X"
                mensagem=f"{m} não é do tipo inteiro" 
            
        elif (x < 1 or x > 3) or (y < 1 or y > 3):
            if (x < 1 or x > 3) and (y < 1 or y > 3):
                mensagem="X,Y não estão no intervalo"
            else:
                m='X' if (x < 1 or x > 3) else 'Y'
                mensagem=f"{m} não esta no intervalo")
            
        if  mensagem != None: 
            raise erro(mensagem)
        return self.__fazer_jogada(y-1,x-1)
                
