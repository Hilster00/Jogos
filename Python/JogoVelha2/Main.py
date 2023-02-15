import os 
import random
import tabuleiro


def limpar_tela():
    os.system("clear")
  
def printar_tabuleiro(tabuleiro):
    a=1
    for i in tabuleiro.tabuleiro():
        for j in i:
            m=f"({a})   " if j == None else f" {j}    "
            print(m,end="")
            a+=1
        print()
        
tabuleiro=tabuleiro.jogo_velha(random.choice(["O","X"]))    

try:
    while(tabuleiro.vencedor == None):
        while(True):
            try:
                #tela 
                limpar_tela()
                print(f"Vez:({tabuleiro.vez})")
                printar_tabuleiro(tabuleiro)
                
                #input 
                jogada=input("Digite a casa desejada:")
                jogada=int(jogada)
                
                #validacao
                if not(0 < jogada < 10): 
                    continue
                
                
                break 
            except:
                pass
        
        #tratamento do input 
        """
        entradas
        X=1|X=2|X=3
        _________
        (1)(2)(3)|Y=1
        (4)(5)(6)|Y=2
        (7)(8)(9)|Y=3
        """
        
        y = (jogada-1)//3+1     
        x= (jogada-1)%3+1
        
        if tabuleiro.jogada(y,x) != None:
            limpar_tela()
            printar_tabuleiro(tabuleiro)
            print(tabuleiro.vencedor,end='')
            if tabuleiro.vencedor != "EMPATE":
                print(" venceu!")
        
except Exception as erro:
    print(f"Exception {erro}")
