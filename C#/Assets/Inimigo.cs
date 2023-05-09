using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Inimigo : MonoBehaviour
{
    // velocidade do inimigo
    public float velocidade = 0.5f;

    // limites do movimento do inimigo
    public float limiteEsquerda = -4.5f;
    public float limiteDireita = 4.5f;

    // variável de direção
    private int direcao = 1;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // atualiza a posição do inimigo
        transform.position += Vector3.right * direcao * velocidade * Time.deltaTime;

        // verifica se o inimigo atingiu um dos limites
        if (transform.position.x < limiteEsquerda || transform.position.x > limiteDireita)
        {
            // inverte a direção do inimigo
            direcao *= -1;
        }
    }

}
