from random import choice
import unicodedata

def limpar(palavra):
    palavra = unicodedata.normalize("NFD", palavra)        
    palavra = palavra.encode("ascii", "ignore")
    palavra = palavra.decode("utf-8")
    palavra = palavra.lower()
    return palavra
    
#bloco que inicializa as variaveis
palavras=['Banana','Bacon','Cartão','Jogo','Pão','Bolo','Leão','Formiga','Kiwi']
lista_letras=[chr(i) for i in range(ord('a'),ord('z')+1)]
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
    letra_limpa=limpar(chute)
    
    #verifica se o chute foi valido
    if letra_limpa in lista_letras:        
        
        if letra_limpa in letras_chutadas:
            print(f"Você já tinha escolhido '{letra_limpa}'")
        else:
            #limpa acentuação da palavra
            palavra_limpa=limpar(palavra)
           
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
        letra_limpa = limpar(letra)
        
        if letra_limpa.lower() in letras_chutadas:
            resposta+=f'{letra}'
        else:
            resposta+='#'
    print(resposta)

#mensagem de vitória    
else:
    print('Parabéns! Você encontrou a palavra')
