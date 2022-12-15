class jogo_velha:
    def __init__(self,primeiro_jogador="X"):
        self.__vez=primeiro_jogador
        self.__tabuleiro=[[None for i in range(3)] for i in range(3)]
        self.vencedor=None
        
    @property
    def vez(self):
        return self.__vez
        
    @vez.setter
    def vez(self,novo):
        if novo in "XO":
            self.__vez=novo
        else:
            self.__vez= "X" if self.__vez == "O" else "O"
            
    def __trocar_vez(self):
        if self.vez=="X":
            self.vez="O"
        else:
            self.vez="X"
            
    def tabuleiro(self,y=None,x=None):
        if x == y:
            #caso especifique uma casa
            if x != None:
                x-=1
                y-=1
                return self.__tabuleiro[y][x]
            #caso não especifique uma casa
            else:
                return [[self.__tabuleiro[i][j] for j in range(3)] for i in range(3)]
        if  x == None:
            y-=1
            return [self.__tabuleiro[y][i] for i in range(3)]
        else:
            ...
    
    def __verifica_vencedor(self):
        linha=False
        vencedor=None
        for i in range(3):
            for j in range(1,3):
                if self.__tabuleiro[i][j] == self.__tabuleiro[i][j-1] != None:
                    linha=True
                    vencedor=self.__tabuleiro[i][j]
                else:
                    linha=False
                    break
        if linha == True:
            return vencedor
        del linha
        coluna=False
        for j in range(3):
            for i in range(1,3):
                if self.__tabuleiro[i][j] == self.__tabuleiro[i-1][j] != None:
                    coluna=True
                    vencedor=self.__tabuleiro[i][j]
                else:
                    coluna=False
                    break 
        if coluna == True:
            return vencedor
        del coluna
        
        diagonal=False
        for i in range(1,3):
            if self.__tabuleiro[i][i] == self.__tabuleiro[i-1][i-1] != None:
                diagonal=True
                vencedor=self.__tabuleiro[i][i]
            else:
                diagonal=False
                break 
        if diagonal == True:
            return vencedor
            
        for i in range(1,3):
            if self.__tabuleiro[i][2-i] == self.__tabuleiro[i-1][1-i] != None:
                diagonal=True
                vencedor=self.__tabuleiro[i][i]
            else:
                diagonal=False
                break 
        if diagonal == True:
            return vencedor
            
        return None
        
    def __fazer_jogada(self,y,x):
        if self.__tabuleiro[y][x] == None:
            self.__tabuleiro[y][x]=self.vez
            self.__trocar_vez()
            self.vencedor=self.__verifica_vencedor()
            return self.vencedor
        else:
            return "Casa Ocupada"
        
    def jogada(self,y=None,x=None):
        
        if y == None or x == None:
            if x==y:
                raise ValueError("X,Y não especificados")
            elif y==None:
                raise ValueError("Y não especificado")
            else:
                raise ValueError("X não especificado")
                
        if type(x) == type(y) == int:
            if (x < 1 or x > 3) or (y < 1 or y > 3):
                if (x < 1 or x > 3) and (y < 1 or y > 3):
                    raise ValueError("X,Y não estão no intervalo")
                elif (x < 1 or x > 3):
                    raise ValueError("X não esta no intervalo")
                else:
                    raise ValueError("Y não esta no intervalo")
            x-=1
            y-=1
            return self.__fazer_jogada(y,x)
                
        else:
            if type(x)!=int and type(y) != int:
                raise TypeError("X,Y não são do tipo inteiro")
            elif type(y)!=int:
                raise TypeError("Y não é do tipo inteiro")
            else:
                raise TypeError("X não é do tipo inteiro")
                
