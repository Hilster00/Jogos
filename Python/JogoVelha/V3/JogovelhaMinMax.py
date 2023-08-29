import random

def exibir_tabuleiro(tabuleiro):
    for linha in tabuleiro:
        print(" | ".join([" " if i == None else i for i in linha]))
        print("-" * 9)

def verificar_vitoria(tabuleiro, jogador):
    # Verificar linhas, colunas e diagonais
    for i in range(3):
        if all(tabuleiro[i][j] == jogador for j in range(3)):
            return True
        if all(tabuleiro[j][i] == jogador for j in range(3)):
            return True
    if all(tabuleiro[i][i] == jogador for i in range(3)) or all(tabuleiro[i][2-i] == jogador for i in range(3)):
        return True
    return False

def verificar_empate(tabuleiro):
    return all(tabuleiro[i][j] != None for i in range(3) for j in range(3))

def minimax(tabuleiro, profundidade, jogador, maquina):
    if jogador == maquina:
        melhor_valor = -float("inf")
        simbolo = maquina
        oponente = 'O' if maquina == 'X' else 'X'
    else:
        melhor_valor = float("inf")
        simbolo = 'O' if maquina == 'X' else 'X'
        oponente = maquina

    if verificar_vitoria(tabuleiro, oponente):
        return -1 if jogador == maquina else 1
    if verificar_vitoria(tabuleiro, simbolo):
        return 1 if jogador == maquina else -1
    if verificar_empate(tabuleiro):
        return 0

    for i in range(3):
        for j in range(3):
            if tabuleiro[i][j] == None:
                tabuleiro[i][j] = jogador
                valor = minimax(tabuleiro, profundidade + 1, oponente, maquina)
                tabuleiro[i][j] = None
                if jogador == maquina:
                    melhor_valor = max(melhor_valor, valor)
                else:
                    melhor_valor = min(melhor_valor, valor)

    return melhor_valor

def jogada_maquina(tabuleiro, maquina):
    melhor_valor = -float("inf")
    melhor_jogada = None

    for i in range(3):
        for j in range(3):
            if tabuleiro[i][j] == None:
                tabuleiro[i][j] = maquina
                valor = minimax(tabuleiro, 0, 'O' if maquina == 'X' else 'X', maquina)
                tabuleiro[i][j] = None
                if valor > melhor_valor:
                    melhor_valor = valor
                    melhor_jogada = (i, j)

    if melhor_jogada:
        tabuleiro[melhor_jogada[0]][melhor_jogada[1]] = maquina

def jogo():
    tabuleiro = [[None for _ in range(3)] for _ in range(3)]
    

    maquina = input("Escolha o símbolo da máquina (X ou O): ")
    while maquina not in ['X', 'O']:
        maquina = input("Escolha um símbolo válido para a máquina (X ou O): ")
    jogador = "X" if maquina == "O" else "O"
    jogador_atual = jogador 
    while True:
        exibir_tabuleiro(tabuleiro)

        if jogador_atual == jogador:
            linha = int(input("Digite a linha (0, 1 ou 2): "))
            coluna = int(input("Digite a coluna (0, 1 ou 2): "))

            if tabuleiro[linha][coluna] == None:
                tabuleiro[linha][coluna] = jogador
                jogador_atual = maquina
            else:
                print("Essa posição já está ocupada. Tente novamente.")
                continue
        else:
            jogada_maquina(tabuleiro, maquina)
            jogador_atual = jogador

        if verificar_vitoria(tabuleiro, jogador):
            exibir_tabuleiro(tabuleiro)
            print("Você venceu!")
            break
        elif verificar_vitoria(tabuleiro, maquina):
            exibir_tabuleiro(tabuleiro)
            print("A máquina venceu!")
            break
        elif verificar_empate(tabuleiro):
            exibir_tabuleiro(tabuleiro)
            print("Empate!")
            break

if __name__ == "__main__":
    jogo()
