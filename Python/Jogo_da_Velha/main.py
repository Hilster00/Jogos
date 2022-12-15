import os 
import random
import tabuleiro


def l():
    os.system("clear")
  

def printar_tabuleiro(tabuleiro):
    a=1
    for i in tabuleiro.tabuleiro():
        for j in i:
            m=f"({a})   " if j == None else f" {j}    "
            print(m,end="")
            a+=1
        print()
        

try:
    tabuleiro=tabuleiro.jogo_velha(random.choice(["O","X"]))
    while(True):
        jogada=0
        while(True):
            try:
                l()
                print(f"Vez:{tabuleiro.vez}")
                printar_tabuleiro(tabuleiro)
                jogada=input("Digite a casa desejada:")
                jogada=int(jogada)
                if -1 < jogada < 10: 
                    break
                else:
                    a=int('a')
            except:
                pass
            
        if jogada < 7:
            y=1 if jogada < 4 else 2
        else:
            y=3
            
        if jogada % 3 == 0:
            x=3
        else:
            x=jogada % 3
            
        if tabuleiro.jogada(y,x) != None:
            l()
            printar_tabuleiro(tabuleiro)
            print(f"{tabuleiro.vencedor} venceu!")
            break
except Exception as erro:
    print(f"Exception {erro}")
