perguntas = {
    "Pergunta 1" : {
            "Pergunta":"Quanto é 3^5 * 3^5 ?",
            "Respostas":{
                    'a':"3^10",
                    'b':"6^5",
                    'c':"3^25",
                    'd':"9^5"
                },
            "Resposta_Certa":'a'
    },
    "Pergunta 2" : {
            "Pergunta":"Qual proximo numero da sequencia 2,3,5,7?",
            "Respostas":{
                    'a':"9",
                    'b':"11",
                    'c':"12",
                    'd':"10"
                },
            "Resposta_Certa":'b'
    },
    "Pergunta 3" : {
            "Pergunta":"Quanto é 5!?",
            "Respostas":{
                    'a':"5",
                    'b':"25",
                    'c':"5*4*3*2*1*0",
                    'd':"5*4!"
                },
            "Resposta_Certa":'d'
    },
}

import os
pontos=0
lista=['a','b','c','d']
for questao in perguntas.values():
    resposta='x'
    while(resposta not in lista):
        os.system("cls")
        print(questao["Pergunta"])
        for alternativa,resposta in questao["Respostas"].items():
            print(f"({alternativa}){resposta}")
        resposta=input("Digite a alternativa escolhida:")
    if resposta.lower() == questao["Resposta_Certa"]:
        pontos+=1
os.system("cls")
print(f"Acertou {pontos} questoes")

os.system("pause")
