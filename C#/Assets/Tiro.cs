using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Tiro : MonoBehaviour
{
    // velocidade do tiro
    public float velocidade = 10f;

    // variável para verificar se o tiro pode ser disparado novamente
    private bool podeAtirar = true;

    // posição inicial do tiro
    private Vector3 posicaoInicial;

    // Start é chamado antes do primeiro frame
    void Start()
    {
        // adiciona um Collider de esfera ao tiro
        SphereCollider collider = gameObject.AddComponent<SphereCollider>();
        collider.radius = 0.2f;
        collider.isTrigger = true;

        // salva a posição inicial do tiro
        posicaoInicial = transform.position;
    }

    // Update é chamado a cada frame
    void Update()
    {
        // verifica se o botão de tiro foi pressionado e se o tiro pode ser disparado novamente
        if (Input.GetKeyDown(KeyCode.Space) && podeAtirar)
        {
            // move o tiro para a posição do jogador
            transform.position = GameObject.FindWithTag("Player").transform.position;

            // dispara o tiro na direção positiva do eixo z
            GetComponent<Rigidbody>().velocity = Vector3.forward * velocidade;

            // define que o tiro não pode ser disparado novamente
            podeAtirar = false;
        }

        // verifica se o tiro saiu da câmera e pode ser disparado novamente
        if (transform.position.z > Camera.main.transform.position.z + 10)
        {
            // move o tiro de volta para a posição inicial
            transform.position = posicaoInicial;

            // define que o tiro pode ser disparado novamente
            podeAtirar = true;
        }
    }

    // chamado quando o tiro colide com outro objeto
    private void OnTriggerEnter(Collider other)
    {
        // verifica se o objeto que colidiu com o tiro tem a tag "Inimigo"
        if (other.gameObject.CompareTag("Inimigo"))
        {
            // move o inimigo para a posição -5 na coordenada y
            other.gameObject.transform.position = new Vector3(other.gameObject.transform.position.x, -5f, other.gameObject.transform.position.z);

            // move o tiro de volta para a posição inicial
            transform.position = posicaoInicial;

            // define que o tiro pode ser disparado novamente
            podeAtirar = true;
        }
    }
}
