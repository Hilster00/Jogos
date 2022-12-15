from random import choice
import unicodedata

#bloco que inicializa as variaveis
palavras=['Banana','Bacon','Cartão','Jogo','Pão','Bolo','Leão','Formiga','Kiwi']
lista_letras=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
palavra=choice(palavras)
resposta=''
letras_chutadas=[]
vidas=5

print("#"*len(palavra))

#jogo
while resposta != palavra:
    
    #bloco de interação com o jogador       
    print(f'Você tem {vidas} vidas')    
    chute=input('Digite o seu palpite:')

    #limpa acentuação
    letra_limpa=chute
    letra_limpa = unicodedata.normalize("NFD", letra_limpa)        
    letra_limpa = letra_limpa.encode("ascii", "ignore")
    letra_limpa = letra_limpa.decode("utf-8")
    letra_limpa=letra_limpa.lower()
    
    #verifica se o chute foi valido
    if letra_limpa in lista_letras:        
        
        if letra_limpa in letras_chutadas:
            print(f"Você já tinha escolhido '{letra_limpa}'")
        else:

            #limpa acentuação da palavra
            palavra_limpa=palavra
            palavra_limpa = unicodedata.normalize("NFD", palavra_limpa)        
            palavra_limpa = palavra_limpa.encode("ascii", "ignore")
            palavra_limpa = palavra_limpa.decode("utf-8")
           
            if letra_limpa in palavra_limpa.lower():                    
                print(f"Parabéns! '{letra_limpa}' está na palavra")           
            else:
                print(f"Que pena! '{letra_limpa}' não está na palavra")
                vidas-=1
            letras_chutadas.append(letra_limpa)
            if vidas == 0:
                print(f"Que pena, você perdeu, a palavra era '{palavra}'")
                break
            
    #bloco chute invalido   
    else:
        print(f"'{letra_limpa}' não é uma opcao valida")
         
    #bloco que atualiza a palavra encontrada e printa na tela
    resposta=''
    for letra in palavra:
            
        #limpa acentuação
        letra_limpa=letra 
        letra_limpa = unicodedata.normalize("NFD", letra_limpa)        
        letra_limpa = letra_limpa.encode("ascii", "ignore")
        letra_limpa = letra_limpa.decode("utf-8")
            
        if letra_limpa.lower() in letras_chutadas:
            print(f'{letra}',end='')
            resposta+=f'{letra}'
        else:
            print('#',end='')
            resposta+='#'
    print()

#mensagem de vitória    
else:
    print('Parabéns! Você encontrou a palavra')
        
