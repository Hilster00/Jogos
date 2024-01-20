import sys
import platform

class TorreHanoi:
    """Classe que representa o jogo da Torre de Hanoi."""

    def __init__(this, quantidade=7):
        """Inicializa a instância da classe TorreHanoi.

        Args:
            quantidade (int): Número de peças na torre (padrão é 7).
        """
        if quantidade < 3:
            quantidade = 7
        this.__quantidade = quantidade
        this.__quantidade_movimentos = 0
        this.__colunas = [
            list(range(1, quantidade + 1)),
            [None for i in range(quantidade)],
            [None for i in range(quantidade)]
        ]
        this.__jogo_finalizado = False

    @property
    def movimentos(this):
        """Propriedade que retorna o número de movimentos realizados."""
        return this.__quantidade_movimentos

    def __peca_movida(this):
        """Incrementa o contador de movimentos quando uma peça é movida."""
        this.__quantidade_movimentos += 1

    @property
    def tabuleiro(this):
        """Propriedade que retorna a disposição atual das peças na torre."""
        return this.__colunas

    @property
    def quantidade(this):
        """Propriedade que retorna a quantidade de peças na torre."""
        return this.__quantidade

    def __str__(this):
        """Representação em string da configuração atual da torre."""
        r = ""
        color_format = '\033[93m|\033[92m{:0>2}\033[93m|\033[92m{:0>2}\033[93m|\033[92m{:0>2}\033[93m|\033[0m\n'
        if platform.system() == 'Windows':
            color_format = '|{:0>2}|{:0>2}|{:0>2}|\n'

        for i in range(this.__quantidade):
            c1 = this.__colunas[0][i] if this.__colunas[0][i] is not None else ""
            c2 = this.__colunas[1][i] if this.__colunas[1][i] is not None else ""
            c3 = this.__colunas[2][i] if this.__colunas[2][i] is not None else ""
            r += color_format.format(c1, c2, c3)
        if platform.system() != 'Windows':
            r += '\033[93m|\033[41;4mC1\033[0;93m|\033[41;4mC2\033[0;93m|\033[41;4mC3\033[0;93m|\033[0m'
        else:
            r += '|C1|C2|C3|'

        return r.replace("00", "**")

    def __vencedor(this):
        """Verifica se o jogo foi vencido."""
        if this.__colunas[1] == list(range(1, this.__quantidade + 1)):
            this.__jogo_finalizado = True
        return this.__jogo_finalizado

    @property
    def partida(this):
        """Propriedade que retorna o estado atual do jogo (Venceu ou Jogando)."""
        return "Venceu" if this.__jogo_finalizado else "Jogando..."

    @property
    def partida_finalizada(this):
        """Propriedade que indica se o jogo foi finalizado."""
        return this.__jogo_finalizado

    def __getitem__(this, movimento):
        """Método que realiza um movimento na torre.

        Args:
            movimento (tuple): Uma tupla representando a origem e destino do movimento.

        Returns:
            str: Mensagem indicando o resultado do movimento.
        """
        coluna_origem, coluna_destino = movimento
        try:
            coluna_origem, coluna_destino = int(coluna_origem), int(coluna_destino)
        except ValueError:
            raise ValueError("As colunas devem ser números inteiros.")
        erro = ""

        if not (1 <= coluna_origem <= 3):
            erro += f"Coluna de origem não tem valor válido.\n"
        if not (1 <= coluna_destino <= 3):
            erro += "Coluna de destino não tem valor válido.\n"
        if coluna_origem == coluna_destino:
            return "A coluna de origem não pode ser a mesma que a de destino"
        if erro != "":
            raise ValueError(f"{erro}")
        del erro

        topo_origem = topo_destino = None
        coluna_origem -= 1
        coluna_destino -= 1
        for origem, destino in zip(this.__colunas[coluna_origem], this.__colunas[coluna_destino]):

            if origem is not None and topo_origem is None:
                topo_origem = origem

            if destino is not None and topo_destino is None:
                topo_destino = destino

            if topo_destino is not None and topo_origem is not None:
                break
        else:
            topo_origem = 0 if topo_origem is None else topo_origem
            topo_destino = 0 if topo_destino is None else topo_destino

            if topo_origem == 0:
                return f"Coluna {coluna_origem + 1} está vazia"

        if topo_origem > topo_destino > 0:
            return "Não pode empilhar sobre o menor"

        vazio = 0
        for i, peca in enumerate(this.__colunas[coluna_destino]):
            if i == this.__quantidade - 1 or peca is not None:
                if i == 0 and peca is not None:
                    return "Não pode empilhar sobre coluna cheia"
                if i == this.__quantidade - 1 and peca is None:
                    vazio = i
                this.__colunas[coluna_destino][vazio], this.__colunas[coluna_origem][
                    this.__colunas[coluna_origem].index(topo_origem)] = \
                    this.__colunas[coluna_origem][this.__colunas[coluna_origem].index(topo_origem)], \
                    this.__colunas[coluna_destino][vazio]
                this.__peca_movida()
                venceu = this.__vencedor()
                return f"Peça {topo_origem} movido para a coluna {coluna_destino}" if not (venceu) else "Você venceu"
            else:
                vazio = i


if __name__ == "__main__":
    quantidade = int(input("Digite a quantidade de peças da torre:"))
    torre = TorreHanoi(quantidade)
    while not (torre.partida_finalizada):
        print(torre)

        origem = input("Digite a coluna de origem da peça:")
        destino = input("Digite a coluna de destino da peça:")
        try:
            print(torre[origem, destino])
        except ValueError as erro:
            print(f"Erro: {erro}")
        print(torre.tabuleiro)
    print(torre)
    print(f"Quantidade de Movimentos:{torre.movimentos}")
