class TorreHanoi:
    def __init__(this,quantidade=7):
        if quantidade<3:
            quantidade=7
        this.__quantidade=quantidade
        this.__quantidade_movimentos=0
        this.__colunas=[
            list(range(1,quantidade+1)),
            [None for i in range(quantidade)],
            [None for i in range(quantidade)]]
        
        this.__jogo_finalizado=False

    #quantidade de movimentos
    @property
    def movimentos(this):
        return this.__quantidade_movimentos
    
    def __peca_movida(this):
        this.__quantidade_movimentos+=1
        
    #verifica a quantidade de pecas no jogo
    @property
    def quantidade(this):
        return this.__quantidade

    #retorna a disposicao de cada coluna
    def __str__(this):
        r=""
        for i in range(this.__quantidade):
            c1 = this.__colunas[0][i] if this.__colunas[0][i] != None else ""
            c2 = this.__colunas[1][i] if this.__colunas[1][i] != None else ""
            c3 = this.__colunas[2][i] if this.__colunas[2][i] != None else ""
            r+=f"\033[93m|\33[92m{c1:0>2}\033[93m|\33[92m{c2:0>2}\033[93m|\33[92m{c3:0>2}\033[93m|\033[0m\n"
        r+=f"\033[93m|\033[41;4mC1\033[0;93m|\033[41;4mC2\033[0;93m|\033[41;4mC3\033[0;93m|\033[0m"
        return r.replace("00","**")
    
    def __vencedor(this):
        if this.__colunas[1] == list(range(1,this.__quantidade+1)):
            this.__jogo_finalizado=True
        return this.__jogo_finalizado
    
    #retorna se o jogo acabou
    @property
    def partida(this):
        return "Venceu" this.__jogo_finalizado == True else "Jogando..."

    @property
    def partida_finalizada(this):
        return this.__jogo_finalizado
    
    def __getitem__(this, movimento):
        coluna_origem, coluna_destino = movimento
        try:
            coluna_origem, coluna_destino = int(coluna_origem), int(coluna_destino)
        except ValueError:
            raise ValueError("As colunas devem ser números inteiros.")
        erro=""
        
        if not (1 <= coluna_origem <= 3):
            erro+=f"Coluna de origem não tem valor válido.\n"
        if not (1 <= coluna_destino <= 3):
            erro+="Coluna de destino não tem valor válido.\n"
        if coluna_origem == coluna_destino:
            return "A coluna de origem não pode ser a mesma que a de destino"
        if erro != "":
            raise ValueError(f"{erro}")
        del erro

        topo_origem=topo_destino=None
        coluna_origem-=1
        coluna_destino-=1
        for origem,destino in zip(this.__colunas[coluna_origem],this.__colunas[coluna_destino]):
            
            #topo da coluna origem
            if origem != None and topo_origem == None:
                topo_origem=origem
 
            #topo da coluna destino
            if destino != None and topo_destino == None:
                topo_destino=destino

            #quebra ciclos desnecessarios
            if topo_destino != None and topo_origem != None:
                break
        else:
            #zera se a coluna estiver vazia
            topo_origem=0 if topo_origem == None else topo_origem
            topo_destino=0 if topo_destino == None else topo_destino

            #movimento de peca que nao existe
            if topo_origem == 0:
                return f"Coluna {coluna_origem+1} está vazia"

        #movimento invalido
        if topo_origem>topo_destino>0:
            return "Não pode empilhar sobre o menor"

        vazio=0
        for i,peca in enumerate(this.__colunas[coluna_destino]):
            if i == this.__quantidade-1 or peca != None:
                if i == 0 and peca != None:
                    return "Não pode empilhar sobre coluna cheia"
                if i == this.__quantidade-1 and peca == None:
                    vazio=i
                this.__colunas[coluna_destino][vazio],this.__colunas[coluna_origem][this.__colunas[coluna_origem].index(topo_origem)] = this.__colunas[coluna_origem][this.__colunas[coluna_origem].index(topo_origem)],this.__colunas[coluna_destino][vazio]
                this.__peca_movida()
                venceu=this.__vencedor()
                return f"Peça {topo_origem} movido para a coluna {coluna_destino}" if not(venceu) else "Você venceu"
            else:
                vazio=i

   
            
if __name__ == "__main__":
    quantidade=int(input("Digite a quantidade de peças da torre:"))
    torre=TorreHanoi(quantidade)
    while not(torre.partida_finalizada):
        print(torre)
        
        origem=input("Digite a coluna de origem da peça:")
        destino=input("Digite a coluna de destino da peça:")
        try:
            print(torre[origem,destino])
        except ValueError as erro:
            print(f"Erro: {erro}")
    print(torre)
    print(f"Quantidade de Movimentos:{torre.movimentos}")
        
"""    
torre=TorreHanoi(3)
print(torre)
print(torre.partida)
print(torre[1, 2])  # Movimento de 1 para 2
print(torre)
try:
    torre["a", 0]  # Tentativa de movimento inválido
except ValueError as e:
    print(f"Erro:{e}")
try:
    torre[0, 4]  # Tentativa de movimento inválido
except ValueError as e:
    print(f"Erro:{e}")"""
