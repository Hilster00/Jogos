using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movimento : MonoBehaviour
{
    // Start is called before the first frame update
    public float velocidade = 0.25f;
    private Vector3 posicaoInicial;

    void Start()
    {
        posicaoInicial = transform.position;
    }

    // Update is called once per frame
    void Update()
    {
        float x = Input.GetAxis("Horizontal");
        float y = Input.GetAxis("Vertical");

        // limita os valores de x e y
        float novaPosicaoX = Mathf.Clamp(transform.position.x + x * velocidade, -4.5f, 4.5f);
        float novaPosicaoY = Mathf.Clamp(transform.position.z + y * velocidade, -4.5f, 4.5f);

        // define a nova posição com os valores limitados
        Vector3 novaPosicao = new Vector3(novaPosicaoX, 0, novaPosicaoY);

        // atualiza a posição do objeto
        transform.position = novaPosicao;
    }

 
}
