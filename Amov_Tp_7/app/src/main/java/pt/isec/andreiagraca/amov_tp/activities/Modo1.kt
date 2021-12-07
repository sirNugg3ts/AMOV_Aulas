package pt.isec.andreiagraca.amov_tp.activities

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pt.isec.andreiagraca.amov_tp.R
import pt.isec.andreiagraca.amov_tp.databinding.ActivityEscolhemodoBinding
import pt.isec.andreiagraca.amov_tp.databinding.ActivityModo1Binding
import kotlin.random.Random


class Modo1 : AppCompatActivity() {
    lateinit var b: ActivityModo1Binding
    val jog0 = "\uD83D\uDD35" //Blue
    val jog1 = "\uD83D\uDD34" //Red
    var jogAtual : String = ""
    val possJogada = "✖"
    var tabuleiro = Array(8) {Array(8) {""} }
    var tabPosicoesFinais = Array(8) { Array(2) {0} }

    var CentCim : Boolean = true     //Cima
    var CentCimDir : Boolean = true  //Cima à direita
    var CentDir : Boolean = true     //Direita
    var CentBaiDir : Boolean = true  //Direita em baixo
    var CentBai : Boolean = true     //Baixo
    var CentBaiEsq : Boolean = true  //Baixo à esquerda
    var CentEsq : Boolean = true     //Esquerda
    var CentCimaEsq : Boolean = true //Cima à esquerda


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityModo1Binding.inflate(layoutInflater)
        setContentView(b.root)
        sorteiaJogador()
        acionaListeners()
        preparaTabuleiro()
        verificaVencedor()
    }

    private fun limpaTabuleiroPosicoesFinais(){
        tabPosicoesFinais[0][0]=-1;tabPosicoesFinais[0][1]=-1;tabPosicoesFinais[1][0]=-1;tabPosicoesFinais[1][1]=-1;tabPosicoesFinais[2][0]=-1
        tabPosicoesFinais[2][1]=-1;tabPosicoesFinais[3][0]=-1;tabPosicoesFinais[3][1]=-1;tabPosicoesFinais[4][0]=-1;tabPosicoesFinais[4][1]=-1
        tabPosicoesFinais[5][0]=-1;tabPosicoesFinais[5][1]=-1;tabPosicoesFinais[6][0]=-1;tabPosicoesFinais[6][1]=-1;tabPosicoesFinais[7][0]=-1;
        tabPosicoesFinais[7][1]=-1;
    }

    private fun confirmaPosicaoValida(linha:Int, coluna:Int): Boolean {

        var j = 0

        limpaTabuleiroPosicoesFinais()

        CentCim = true     //Cima
        CentCimDir = true  //Cima à direita
        CentDir = true     //Direita
        CentBaiDir = true  //Direita em baixo
        CentBai = true     //Baixo
        CentBaiEsq = true  //Baixo à esquerda
        CentEsq = true     //Esquerda
        CentCimaEsq = true //Cima à esquerda

        //Verificar Vizinhança Vazia
        if(linha!=7 && linha!=0 && coluna!=0 && coluna!=7){
            if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha+1][coluna+1]=="" && tabuleiro[linha][coluna+1]==""
                && tabuleiro[linha-1][coluna+1]=="" && tabuleiro[linha-1][coluna]=="" && tabuleiro[linha-1][coluna-1]==""
                && tabuleiro[linha][coluna-1]=="" && tabuleiro[linha+1][coluna-1]=="")
                return false
        }
        else{
            if(linha==0 && coluna!=0 && coluna!=7) {
                if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha+1][coluna+1]=="" && tabuleiro[linha][coluna+1]==""
                    && tabuleiro[linha][coluna-1]=="" && tabuleiro[linha+1][coluna-1]=="")
                    return false
            }


            if(coluna==0 && linha!=0 && linha!=7){
                if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha+1][coluna+1]=="" && tabuleiro[linha][coluna+1]==""
                    && tabuleiro[linha-1][coluna+1]=="" && tabuleiro[linha-1][coluna]=="")
                    return false
            }


            if(linha==7 && coluna!=0 && coluna!=7){
                if(tabuleiro[linha][coluna+1]=="" && tabuleiro[linha-1][coluna+1]=="" && tabuleiro[linha-1][coluna]==""
                    && tabuleiro[linha-1][coluna-1]=="" && tabuleiro[linha][coluna-1]=="")
                    return false
            }


            if(coluna==7 && linha!=0 && linha!=7){
                if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha-1][coluna]=="" && tabuleiro[linha-1][coluna-1]==""
                    && tabuleiro[linha][coluna-1]=="" && tabuleiro[linha+1][coluna-1]=="")
                    return false
            }


            if(linha==0 && coluna==0){
                if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha+1][coluna+1]=="" && tabuleiro[linha][coluna+1]=="")
                    return false
            }


            if(linha==0 && coluna==7){
                if(tabuleiro[linha+1][coluna]=="" && tabuleiro[linha][coluna-1]=="" && tabuleiro[linha+1][coluna-1]=="")
                    return false
            }


            if(linha==7 && coluna==7){
                if(tabuleiro[linha-1][coluna]=="" && tabuleiro[linha-1][coluna-1]=="" && tabuleiro[linha][coluna-1]=="")
                    return false
            }


            if(linha==7 && coluna==0){
                if(tabuleiro[linha][coluna+1]=="" && tabuleiro[linha-1][coluna+1]=="" && tabuleiro[linha-1][coluna]=="")
                    return false
            }
        }

        //VERIFICAR COMBO PEÇAS (VERTICAL E HORIZONTAl)
        //Centro -> Cima
        if(linha == 0)
            CentCim=false

        for(i in linha-1 downTo 0){

            println("Centro->Cima $i // $coluna")
            if(tabuleiro[i][coluna]==""){
                CentCim = false
                break
            }

             if(tabuleiro[i][coluna] == jogAtual){
                 if(i==linha-1)
                     CentCim=false
                 else {
                     tabPosicoesFinais[0][0] = i; tabPosicoesFinais[0][1] = coluna;
                 }

                 break
             }

            if(i==0)
                CentCim = false
        }


        //Centro -> Direita
        if(coluna ==7)
            CentDir = false

        for(i in coluna+1 until 8){

            println("Centro->Direita $linha // $i")
            if(tabuleiro[linha][i] == ""){
                CentDir = false
                break
            }

            if(tabuleiro[linha][i] == jogAtual){
                if(i==coluna+1)
                    CentDir=false
                else {
                    tabPosicoesFinais[2][0] = linha; tabPosicoesFinais[2][1] = i
                }

                break
            }

            if(i==7)
                CentDir = false
        }

        //Centro -> Baixo
        if(linha == 7)
            CentBai = false

        for(i in linha+1 until 8){

            println("Centro->Baixo $i // $coluna")
            if(tabuleiro[i][coluna] == ""){
                CentBai = false
                break
            }

            if(tabuleiro[i][coluna] == jogAtual){
                if(i==linha+1)
                    CentBai=false
                else {
                    tabPosicoesFinais[4][0] = i; tabPosicoesFinais[4][1] = coluna
                }

                break
            }

            if(i==7)
                CentBai = false
        }


        //Centro -> Esquerda
        if(coluna == 0)
            CentEsq=false

        for(i in coluna-1 downTo 0){

            println("Centro->Esquerda $linha // $i")
            if(tabuleiro[linha][i] == ""){
                CentEsq = false
                break
            }

            if(tabuleiro[linha][i] == jogAtual){
                if(i==coluna-1)
                    CentEsq = false
                else {
                    tabPosicoesFinais[6][0] = linha; tabPosicoesFinais[6][1] = i
                }

                break
            }

            if(i==0)
                CentEsq = false
        }


        //VERIFICAR COMBO PEÇAS (DIAGONAIS)
        //Centro -> Cima//Direira
        if(linha==0 || coluna==7)
            CentCimDir=false
        else{
            j = coluna+1

            for(i in linha-1 downTo 0){

                println("Centro->Cima-Direita $i // $j")
                if(tabuleiro[i][j] == ""){
                    CentCimDir = false
                    break
                }

                if(tabuleiro[i][j]==jogAtual){
                    if(i==linha-1 && j==coluna+1)
                        CentCimDir=false
                    else {
                        tabPosicoesFinais[1][0] = i; tabPosicoesFinais[1][1] = j
                    }

                    break
                }

                if(j==7) {
                    CentCimDir = false
                    break
                }

                j++

                if(i==0) {
                    CentCimDir = false
                }
            }
        }



        //Centro-> Baixo//Direita
        if(linha==7 || coluna==7)
            CentBaiDir=false
        else {
            j = coluna+1

            for(i in linha+1 until 8){

                println("Centro->Baixo-Direita $i // $j")
                if(tabuleiro[i][j] == ""){
                    CentBaiDir = false
                    break
                }

                if(tabuleiro[i][j]==jogAtual){
                    if(i==linha+1 && j==coluna+1)
                        CentBaiDir=false
                    else {
                        tabPosicoesFinais[3][0] = i; tabPosicoesFinais[3][1] = j
                    }

                    break
                }


                if(j==7) {
                    CentBaiDir = false
                    break
                }

                j++

                if(i==7) {
                    CentBaiDir = false
                }
            }
        }



        //Centro -> Baixo//Esquerda

        if(linha==7 || coluna==0)
            CentBaiEsq=false
        else{
            j = coluna-1

            for(i in linha+1 until 8){

                println("Centro->Baixo-Esquerda $i // $j")
                if(tabuleiro[i][j] == ""){
                    CentBaiEsq = false
                    break
                }

                if(tabuleiro[i][j]==jogAtual){
                    if(i==linha+1 && j==coluna-1)
                        CentBaiEsq=false
                    else {
                        tabPosicoesFinais[5][0] = i; tabPosicoesFinais[5][1] = j
                    }

                    break
                }

                if(j==0) {
                    CentBaiEsq = false
                    break
                }

                j--

                if(i==7) {
                    CentBaiEsq = false
                }
            }
        }




        //Centro -> Esquerda//Cima
        if(linha==0 || coluna==0)
            CentCimaEsq=false
        else{
            j = coluna-1

            for(i in linha-1 downTo  0){

                println("Centro->Cima-Direita $i // $j")
                if(tabuleiro[i][j] == ""){
                    CentCimaEsq = false
                    break
                }

                if(tabuleiro[i][j]==jogAtual){
                    if(i==linha-1 && j==coluna-1)
                        CentCimaEsq=false
                    else {
                        tabPosicoesFinais[7][0] = i; tabPosicoesFinais[7][1] = j
                    }

                    break
                }

                if(j==0) {
                    CentCimaEsq = false
                    break
                }

                j--

                if(i==0) {
                    CentCimaEsq = false
                }
            }
        }


        println("\n\nCentro->Cima == $CentCim")
        println("Centro->Cima//Direita == $CentCimDir" )
        println("Centro->Direita == $CentDir")
        println("Centro->Baixo//Direita == $CentBaiDir")
        println("Centro->Baixo == $CentBai")
        println("Centro->Baixo//Esquerda == $CentBaiEsq")
        println("Centro->Esquerda == $CentEsq")
        println("Centro->Cima//Esquerda == $CentCimaEsq")

        println("\n\nCentro->Cima == " + tabPosicoesFinais[0][0] + "//" + tabPosicoesFinais[0][1])
        println("Centro->Cima//Direita == " + tabPosicoesFinais[1][0] + "//" + tabPosicoesFinais[1][1])
        println("Centro->Direita == " + tabPosicoesFinais[2][0] + "//" + tabPosicoesFinais[2][1])
        println("Centro->Baixo//Direita == " + tabPosicoesFinais[3][0] + "//" + tabPosicoesFinais[3][1])
        println("Centro->Baixo == " + tabPosicoesFinais[4][0] + "//" + tabPosicoesFinais[4][1])
        println("Centro->Baixo//Esquerda == " + tabPosicoesFinais[5][0] + "//" + tabPosicoesFinais[5][1])
        println("Centro->Esquerda == " + tabPosicoesFinais[6][0] + "//" + tabPosicoesFinais[6][1])
        println("Centro->Cima//Esquerda == " + tabPosicoesFinais[7][0] + "//" + tabPosicoesFinais[7][1])

        return !(!CentCim && !CentCimDir && !CentDir && !CentBaiDir && !CentBai && !CentBaiEsq && !CentEsq && !CentCimaEsq)
    }




    private fun atualizaTabuleiro(linha:Int, coluna:Int){
        tabuleiro[linha][coluna] = jogAtual

       if(CentCim){
           for(i in linha downTo tabPosicoesFinais[0][0]){
               tabuleiro[i][coluna] = jogAtual
           }
       }

       if(CentCimDir){
           var j=coluna
           for(i in linha downTo tabPosicoesFinais[1][0]){
               tabuleiro[i][j] = jogAtual
               j++
           }
       }

       if(CentDir){
           for(i in coluna until tabPosicoesFinais[2][1]){
               tabuleiro[linha][i] = jogAtual
           }
       }

       if(CentBaiDir){
           var j = coluna
           for(i in linha until tabPosicoesFinais[3][0]){
               tabuleiro[i][j] = jogAtual
               j++
           }
       }

       if(CentBai){
           for(i in linha until tabPosicoesFinais[4][0]){
               tabuleiro[i][coluna] = jogAtual
           }
       }

       if(CentBaiEsq){
           var j = coluna
           for(i in linha until  tabPosicoesFinais[5][0]){
               tabuleiro[i][j] = jogAtual
               j--
           }
       }

       if(CentEsq){
           for(i in coluna downTo tabPosicoesFinais[6][1]){
               tabuleiro[linha][i] = jogAtual
           }
       }

       if(CentCimaEsq){
           var j=coluna
           for(i in linha downTo tabPosicoesFinais[7][0]){
               tabuleiro[i][j] = jogAtual
               j--
           }
       }

        atualizaVista()
        verificaVencedor()
    }

    private fun verificaVencedor() {
        var pjog0=0
        var pjog1=0
        var parcelVazias = false

       for(i in 0 until 8){
           for(j in 0 until 8){

               if(tabuleiro[i][j]=="")
                   parcelVazias=true

               if(tabuleiro[i][j]==jog0)
                   pjog0++

               if(tabuleiro[i][j]==jog1){
                   pjog1++
               }

           }
       }

        b.numPecasJog0.text = pjog0.toString()
        b.numPecasJog1.text = pjog1.toString()

       if(pjog0 == 0){
           val builder = AlertDialog.Builder(this)
           builder.setTitle("FIM DE JOGO")
           builder.setMessage("O vencedor do jogo é o jogador com as peças \uD83D\uDD34!!")


           builder.setPositiveButton(R.string.novJog) { dialog, which ->
               preparaNovoJogo()
           }

           builder.setNegativeButton(R.string.consultarTab) { dialog, which ->
               b.btnTrocaPecas.isEnabled = false
               b.btnPecaBomba.isEnabled = false
               b.btnPassaVez.isEnabled = false
               b.txtIndicadorDeJogador.text = "O vencedor é "
               b.CorAtual.text = "\uD83D\uDD34"
           }

           builder.show()
       }

       if(pjog1 == 0){
           val builder = AlertDialog.Builder(this)
           builder.setTitle("FIM DE JOGO")
           builder.setMessage("O vencedor do jogo é o jogador com as peças \uD83D\uDD35!!")


           builder.setPositiveButton(R.string.novJog) { dialog, which ->
               preparaNovoJogo()
           }

           builder.setNegativeButton(R.string.consultarTab) { dialog, which ->
               b.btnTrocaPecas.isEnabled = false
               b.btnPecaBomba.isEnabled = false
               b.btnPassaVez.isEnabled = false
               b.txtIndicadorDeJogador.text = "O vencedor é "
               b.CorAtual.text = "\uD83D\uDD35"
           }

           builder.show()
       }

       if(!parcelVazias && pjog0>pjog1){
           val builder = AlertDialog.Builder(this)
           builder.setTitle("FIM DE JOGO")
           builder.setMessage("O vencedor do jogo é o jogador com as peças \uD83D\uDD35!!")


           builder.setPositiveButton(R.string.novJog) { dialog, which ->
               preparaNovoJogo()
           }

           builder.setNegativeButton(R.string.consultarTab) { dialog, which ->
               b.btnTrocaPecas.isEnabled = false
               b.btnPecaBomba.isEnabled = false
               b.btnPassaVez.isEnabled = false
               b.txtIndicadorDeJogador.text = "O vencedor é "
               b.CorAtual.text = "\uD83D\uDD35"
           }

           builder.show()
       }

       if(!parcelVazias && pjog1>pjog0){
           val builder = AlertDialog.Builder(this)
           builder.setTitle("FIM DE JOGO")
           builder.setMessage("O vencedor do jogo é o jogador com as peças \uD83D\uDD34!!")


           builder.setPositiveButton(R.string.novJog) { dialog, which ->
               preparaNovoJogo()
           }

           builder.setNegativeButton(R.string.consultarTab) { dialog, which ->
               b.btnTrocaPecas.isEnabled = false
               b.btnPecaBomba.isEnabled = false
               b.btnPassaVez.isEnabled = false
               b.txtIndicadorDeJogador.text = "O vencedor é "
               b.CorAtual.text = "\uD83D\uDD34"
           }

           builder.show()
       }
    }

    private fun preparaNovoJogo() {
        for(i in 0 until 8){
            for(j in 0 until 8){
                tabuleiro[i][j]=""
            }
        }

        sorteiaJogador()
        preparaTabuleiro()
        atualizaVista()
        verificaVencedor()
    }

    private fun preparaTabuleiro() {
        tabuleiro[3][3] = "\uD83D\uDD35"
        tabuleiro[4][3] = "\uD83D\uDD34"
        tabuleiro[3][4] = "\uD83D\uDD34"
        tabuleiro[4][4] = "\uD83D\uDD35"

        atualizaVista()
    }

    private fun atualizaVista() {
        b.btn00.text = tabuleiro[0][0]; b.btn01.text = tabuleiro[0][1]; b.btn02.text = tabuleiro[0][2]; b.btn03.text = tabuleiro[0][3]; b.btn04.text = tabuleiro[0][4]; b.btn05.text = tabuleiro[0][5]; b.btn06.text = tabuleiro[0][6]; b.btn07.text = tabuleiro[0][7]
        b.btn10.text = tabuleiro[1][0]; b.btn11.text = tabuleiro[1][1]; b.btn12.text = tabuleiro[1][2]; b.btn13.text = tabuleiro[1][3]; b.btn14.text = tabuleiro[1][4]; b.btn15.text = tabuleiro[1][5]; b.btn16.text = tabuleiro[1][6]; b.btn17.text = tabuleiro[1][7]
        b.btn20.text = tabuleiro[2][0]; b.btn21.text = tabuleiro[2][1]; b.btn22.text = tabuleiro[2][2]; b.btn23.text = tabuleiro[2][3]; b.btn24.text = tabuleiro[2][4]; b.btn25.text = tabuleiro[2][5]; b.btn26.text = tabuleiro[2][6]; b.btn27.text = tabuleiro[2][7]
        b.btn30.text = tabuleiro[3][0]; b.btn31.text = tabuleiro[3][1]; b.btn32.text = tabuleiro[3][2]; b.btn33.text = tabuleiro[3][3]; b.btn34.text = tabuleiro[3][4]; b.btn35.text = tabuleiro[3][5]; b.btn36.text = tabuleiro[3][6]; b.btn37.text = tabuleiro[3][7]
        b.btn40.text = tabuleiro[4][0]; b.btn41.text = tabuleiro[4][1]; b.btn42.text = tabuleiro[4][2]; b.btn43.text = tabuleiro[4][3]; b.btn44.text = tabuleiro[4][4]; b.btn45.text = tabuleiro[4][5]; b.btn46.text = tabuleiro[4][6]; b.btn47.text = tabuleiro[4][7]
        b.btn50.text = tabuleiro[5][0]; b.btn51.text = tabuleiro[5][1]; b.btn52.text = tabuleiro[5][2]; b.btn53.text = tabuleiro[5][3]; b.btn54.text = tabuleiro[5][4]; b.btn55.text = tabuleiro[5][5]; b.btn56.text = tabuleiro[5][6]; b.btn57.text = tabuleiro[5][7]
        b.btn60.text = tabuleiro[6][0]; b.btn61.text = tabuleiro[6][1]; b.btn62.text = tabuleiro[6][2]; b.btn63.text = tabuleiro[6][3]; b.btn64.text = tabuleiro[6][4]; b.btn65.text = tabuleiro[6][5]; b.btn66.text = tabuleiro[6][6]; b.btn67.text = tabuleiro[6][7]
        b.btn70.text = tabuleiro[7][0]; b.btn71.text = tabuleiro[7][1]; b.btn72.text = tabuleiro[7][2]; b.btn73.text = tabuleiro[7][3]; b.btn74.text = tabuleiro[7][4]; b.btn75.text = tabuleiro[7][5]; b.btn76.text = tabuleiro[7][6]; b.btn77.text = tabuleiro[7][7]
    }


    private fun sorteiaJogador() {
        if(Random.nextInt(0,2)==1) {
            jogAtual = jog1
            b.CorAtual.text = jog1
        }
        else {
            jogAtual = jog0
            b.CorAtual.text = jog0
        }
    }

    private fun trocaJogador(){
        if(jogAtual == jog1) {
            jogAtual = jog0
            b.CorAtual.text = jog0
        }
        else {
            jogAtual = jog1
            b.CorAtual.text = jog1
        }
    }



    private fun acionaListeners(){

        //BOTÕES ESPECIAIS
        b.btnPecaBomba.setOnClickListener{
            TODO("Not yet implemented")
        }

        b.btnTrocaPecas.setOnClickListener{
            TODO("Not yet implemented")
        }

        b.btnPassaVez.setOnClickListener{
            trocaJogador()
        }


        //0ª FILA DE PEÇAS
        b.btn00.setOnClickListener{
            if((b.btn00.text.isEmpty() || b.btn00.text.isBlank()) && confirmaPosicaoValida(0,0)) {
                atualizaTabuleiro(0,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn01.setOnClickListener{
            if((b.btn01.text.isEmpty() || b.btn01.text.isBlank()) && confirmaPosicaoValida(0,1)) {
                atualizaTabuleiro(0,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn02.setOnClickListener{
            if((b.btn02.text.isEmpty() || b.btn02.text.isBlank()) && confirmaPosicaoValida(0,2)) {
                atualizaTabuleiro(0,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn03.setOnClickListener{
            if((b.btn03.text.isEmpty() || b.btn03.text.isBlank()) && confirmaPosicaoValida(0,3)) {
                atualizaTabuleiro(0,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn04.setOnClickListener{
            if((b.btn04.text.isEmpty() || b.btn04.text.isBlank()) && confirmaPosicaoValida(0,4)) {
                atualizaTabuleiro(0,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn05.setOnClickListener{
            if((b.btn05.text.isEmpty() || b.btn05.text.isBlank()) && confirmaPosicaoValida(0,5)) {
                atualizaTabuleiro(0,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn06.setOnClickListener{
            if((b.btn06.text.isEmpty() || b.btn06.text.isBlank()) && confirmaPosicaoValida(0,6)) {
                atualizaTabuleiro(0,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn07.setOnClickListener{
            if((b.btn07.text.isEmpty() || b.btn07.text.isBlank()) && confirmaPosicaoValida(0,7)) {
                atualizaTabuleiro(0,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }










        //1ª FILA DE PEÇAS
        b.btn10.setOnClickListener{
            if((b.btn10.text.isEmpty() || b.btn10.text.isBlank()) && confirmaPosicaoValida(1,0)) {
                atualizaTabuleiro(1,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn11.setOnClickListener{
            if((b.btn11.text.isEmpty() || b.btn11.text.isBlank()) && confirmaPosicaoValida(1,1)) {
                atualizaTabuleiro(1,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn12.setOnClickListener{
            if((b.btn12.text.isEmpty() || b.btn12.text.isBlank()) && confirmaPosicaoValida(1,2)) {
                atualizaTabuleiro(1,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn13.setOnClickListener{
            if((b.btn13.text.isEmpty() || b.btn13.text.isBlank()) && confirmaPosicaoValida(1,3)) {
                atualizaTabuleiro(1,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn14.setOnClickListener{
            if((b.btn14.text.isEmpty() || b.btn14.text.isBlank()) && confirmaPosicaoValida(1,4)) {
                atualizaTabuleiro(1,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn15.setOnClickListener{
            if((b.btn15.text.isEmpty() || b.btn15.text.isBlank()) && confirmaPosicaoValida(1,5)) {
                atualizaTabuleiro(1,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn16.setOnClickListener{
            if((b.btn16.text.isEmpty() || b.btn16.text.isBlank()) && confirmaPosicaoValida(1,6)) {
                atualizaTabuleiro(1,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn17.setOnClickListener{
            if((b.btn17.text.isEmpty() || b.btn17.text.isBlank()) && confirmaPosicaoValida(1,7)) {
                atualizaTabuleiro(1,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }













        //2ª FILA DE PEÇAS
        b.btn20.setOnClickListener{
            if((b.btn20.text.isEmpty() || b.btn20.text.isBlank()) &&  confirmaPosicaoValida(2,0)) {
                atualizaTabuleiro(2,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn21.setOnClickListener{
            if((b.btn21.text.isEmpty() || b.btn21.text.isBlank()) && confirmaPosicaoValida(2,1)) {
                atualizaTabuleiro(2,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn22.setOnClickListener{
            if((b.btn22.text.isEmpty() || b.btn22.text.isBlank()) && confirmaPosicaoValida(2,2)) {
                atualizaTabuleiro(2,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn23.setOnClickListener{
            if((b.btn23.text.isEmpty() || b.btn23.text.isBlank()) && confirmaPosicaoValida(2,3)) {
                atualizaTabuleiro(2,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn24.setOnClickListener{
            if((b.btn24.text.isEmpty() || b.btn24.text.isBlank()) && confirmaPosicaoValida(2,4)) {
                atualizaTabuleiro(2,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn25.setOnClickListener{
            if((b.btn25.text.isEmpty() || b.btn25.text.isBlank()) && confirmaPosicaoValida(2,5)) {
                atualizaTabuleiro(2,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn26.setOnClickListener{
            if((b.btn26.text.isEmpty() || b.btn26.text.isBlank()) && confirmaPosicaoValida(2,6)) {
                atualizaTabuleiro(2,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn27.setOnClickListener{
            if((b.btn27.text.isEmpty() || b.btn27.text.isBlank()) && confirmaPosicaoValida(2,7)) {
                atualizaTabuleiro(2,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }
















        //3ª FILA DE PEÇAS
        b.btn30.setOnClickListener{
            if((b.btn30.text.isEmpty() || b.btn30.text.isBlank()) && confirmaPosicaoValida(3,0)) {
                atualizaTabuleiro(3,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn31.setOnClickListener{
            if((b.btn31.text.isEmpty() || b.btn31.text.isBlank()) && confirmaPosicaoValida(3,1)) {
                atualizaTabuleiro(3,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn32.setOnClickListener{
            if((b.btn32.text.isEmpty() || b.btn32.text.isBlank()) && confirmaPosicaoValida(3,2)) {
                atualizaTabuleiro(3,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn33.setOnClickListener{
            if((b.btn33.text.isEmpty() || b.btn33.text.isBlank()) && confirmaPosicaoValida(3,3)) {
                atualizaTabuleiro(3,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn34.setOnClickListener{
            if((b.btn34.text.isEmpty() || b.btn34.text.isBlank()) && confirmaPosicaoValida(3,4)) {
                atualizaTabuleiro(3,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn35.setOnClickListener{
            if((b.btn35.text.isEmpty() || b.btn35.text.isBlank()) && confirmaPosicaoValida(3,5)) {
                atualizaTabuleiro(3,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn36.setOnClickListener{
            if((b.btn36.text.isEmpty() || b.btn36.text.isBlank()) && confirmaPosicaoValida(3,6)) {
                atualizaTabuleiro(3,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn37.setOnClickListener{
            if((b.btn37.text.isEmpty() || b.btn37.text.isBlank()) && confirmaPosicaoValida(3,7)) {
                atualizaTabuleiro(3,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }
















        //4º FILA DE PEÇAS
        b.btn40.setOnClickListener{
            if((b.btn40.text.isEmpty() || b.btn40.text.isBlank()) && confirmaPosicaoValida(4,0)) {
                atualizaTabuleiro(4,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn41.setOnClickListener{
            if((b.btn41.text.isEmpty() || b.btn41.text.isBlank()) && confirmaPosicaoValida(4,1)) {
                atualizaTabuleiro(4,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn42.setOnClickListener{
            if((b.btn42.text.isEmpty() || b.btn42.text.isBlank()) && confirmaPosicaoValida(4,2)) {
                atualizaTabuleiro(4,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn43.setOnClickListener{
            if((b.btn43.text.isEmpty() || b.btn43.text.isBlank()) && confirmaPosicaoValida(4,3)) {
                atualizaTabuleiro(4,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn44.setOnClickListener{
            if((b.btn44.text.isEmpty() || b.btn44.text.isBlank()) && confirmaPosicaoValida(4,4)) {
                atualizaTabuleiro(4,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn45.setOnClickListener{
            if((b.btn45.text.isEmpty() || b.btn45.text.isBlank()) && confirmaPosicaoValida(4,5)) {
                atualizaTabuleiro(4,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn46.setOnClickListener{
            if((b.btn46.text.isEmpty() || b.btn46.text.isBlank()) && confirmaPosicaoValida(4,6)) {
                atualizaTabuleiro(4,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn47.setOnClickListener{
            if((b.btn47.text.isEmpty() || b.btn47.text.isBlank()) && confirmaPosicaoValida(4,7)) {
                atualizaTabuleiro(4,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }
















        //5ª FILA DE PEÇAS
        b.btn50.setOnClickListener{
            if((b.btn50.text.isEmpty() || b.btn50.text.isBlank()) && confirmaPosicaoValida(5,0)) {
                atualizaTabuleiro(5,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn51.setOnClickListener{
            if((b.btn51.text.isEmpty() || b.btn51.text.isBlank()) && confirmaPosicaoValida(5,1)) {
                atualizaTabuleiro(5,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn52.setOnClickListener{
            if((b.btn52.text.isEmpty() || b.btn52.text.isBlank()) && confirmaPosicaoValida(5,2)) {
                atualizaTabuleiro(5,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn53.setOnClickListener{
            if((b.btn53.text.isEmpty() || b.btn53.text.isBlank()) && confirmaPosicaoValida(5,3)) {
                atualizaTabuleiro(5,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn54.setOnClickListener{
            if((b.btn54.text.isEmpty() || b.btn54.text.isBlank()) && confirmaPosicaoValida(5,4)) {
                atualizaTabuleiro(5,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn55.setOnClickListener{
            if((b.btn55.text.isEmpty() || b.btn55.text.isBlank()) && confirmaPosicaoValida(5,5)) {
                atualizaTabuleiro(5,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn56.setOnClickListener{
            if((b.btn56.text.isEmpty() || b.btn56.text.isBlank()) && confirmaPosicaoValida(5,6)) {
                atualizaTabuleiro(5,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn57.setOnClickListener{
            if((b.btn57.text.isEmpty() || b.btn57.text.isBlank()) &&  confirmaPosicaoValida(5,7)) {
                atualizaTabuleiro(5,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }


















        //6ª FILA DE PEÇAS
        b.btn60.setOnClickListener{
            if((b.btn60.text.isEmpty() || b.btn60.text.isBlank()) && confirmaPosicaoValida(6,0)) {
                atualizaTabuleiro(6,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn61.setOnClickListener{
            if((b.btn61.text.isEmpty() || b.btn61.text.isBlank()) && confirmaPosicaoValida(6,1)) {
                atualizaTabuleiro(6,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn62.setOnClickListener{
            if((b.btn62.text.isEmpty() || b.btn62.text.isBlank()) && confirmaPosicaoValida(6,2)) {
                atualizaTabuleiro(6,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn63.setOnClickListener{
            if((b.btn63.text.isEmpty() || b.btn63.text.isBlank()) && confirmaPosicaoValida(6,3)) {
                atualizaTabuleiro(6,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn64.setOnClickListener{
            if((b.btn64.text.isEmpty() || b.btn64.text.isBlank()) && confirmaPosicaoValida(6,4)) {
                atualizaTabuleiro(6,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn65.setOnClickListener{
            if((b.btn65.text.isEmpty() || b.btn65.text.isBlank()) && confirmaPosicaoValida(6,5)) {
                atualizaTabuleiro(6,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn66.setOnClickListener{
            if((b.btn66.text.isEmpty() || b.btn66.text.isBlank()) && confirmaPosicaoValida(6,6)) {
                atualizaTabuleiro(6,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn67.setOnClickListener{
            if((b.btn67.text.isEmpty() || b.btn67.text.isBlank()) && confirmaPosicaoValida(6,7)) {
                atualizaTabuleiro(6,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }
















        //7ª FILA DE PEÇAS
        b.btn70.setOnClickListener{
            if((b.btn70.text.isEmpty() || b.btn70.text.isBlank()) && confirmaPosicaoValida(7,0)) {
                atualizaTabuleiro(7,0)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn71.setOnClickListener{
            if((b.btn71.text.isEmpty() || b.btn71.text.isBlank()) && confirmaPosicaoValida(7,1)) {
                atualizaTabuleiro(7,1)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn72.setOnClickListener{
            if((b.btn72.text.isEmpty() || b.btn72.text.isBlank()) && confirmaPosicaoValida(7,2)) {
                atualizaTabuleiro(7,2)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn73.setOnClickListener{
            if((b.btn73.text.isEmpty() || b.btn73.text.isBlank()) && confirmaPosicaoValida(7,3)) {
                atualizaTabuleiro(7,3)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn74.setOnClickListener{
            if((b.btn74.text.isEmpty() || b.btn74.text.isBlank()) && confirmaPosicaoValida(7,4)) {
                atualizaTabuleiro(7,4)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn75.setOnClickListener{
            if((b.btn75.text.isEmpty() || b.btn75.text.isBlank()) && confirmaPosicaoValida(7,5)) {
                atualizaTabuleiro(7,5)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn76.setOnClickListener{
            if((b.btn76.text.isEmpty() || b.btn76.text.isBlank()) && confirmaPosicaoValida(7,6)) {
                atualizaTabuleiro(7,6)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }

        b.btn77.setOnClickListener{
            if((b.btn77.text.isEmpty() || b.btn77.text.isBlank()) && confirmaPosicaoValida(7,7)) {
                atualizaTabuleiro(7,7)
                trocaJogador()
            }
            else
                Toast.makeText(applicationContext, "Posição invalida para jogar...", Toast.LENGTH_SHORT).show()
        }
    }
}