import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static class Dados{
    public
        int ano;
        int score;
        String terapia;
        String problema;
        String parteCorpo;

        Dados(){
            ano = 0;
            score = 0;
        }
    }

    static Dados[] PEDro = new Dados[6440];
    static int contadorGlobal = 0;
    static String[] corpo = new String[] {"head or neck", "upper arm, shoulder or shoulder girdle", "forearm or elbow", "hand or wrist","chest", "thoracic spine", "lumbar spine, sacro-iliac joint or pelvis", "perineum or genito-urinary system", "thigh or hip", "lower leg or knee", "foot or ankle", "[no appropriate value in this field]"};
    static String[] problemas = new String[] {"difficulty with sputum clearance", "frailty", "impaired ventilation", "incontinence", "motor incoordination", "muscle shortening, reduced joint compliance", "muscle weakness", "oedema", "pain", "reduced exercise tolerance", "reduced work tolerance", "skin lesion, wound, burn", "[no appropriate value in this field]"};
    static String[] terapia = new String[] {"acupuncture", "behaviour modification", "education", "electrotherapies, heat, cold", "fitness training", "health promotion", "hydrotherapy, balneotherapy neurodevelopmental therapy, neurofacilitation", "orthoses, taping, splinting", "respiratory therapy", "skill training", "strength training", "stretching, mobilisation, manipulation, massage [no appropriate value in this field]"};



    static void lerDados() throws IOException{
        try{
            File arquivo = new File("src\\Planilha PEDro Formatada.txt");
            FileReader arquivo2 = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(arquivo2);
            String linha = leitor.readLine();


             int i=0;
            while(linha != null) {
                String[] separador = linha.split(";");
                String anoArquivo = separador[0];
                String scoreArquivo = separador[1];
                String terapiaArquivo = separador[2];
                String problemaArquivo = separador[3];
                String parteCorpoArquivo = separador[4];

                anoArquivo = anoArquivo.trim();
                scoreArquivo = scoreArquivo.trim();

                PEDro[i].ano = Integer.parseInt(anoArquivo);
                PEDro[i].score = Integer.parseInt(scoreArquivo);
                PEDro[i].terapia = terapiaArquivo.trim();
                PEDro[i].problema = problemaArquivo.trim();
                PEDro[i].parteCorpo = parteCorpoArquivo.trim();

                linha = leitor.readLine();
                i++;
                contadorGlobal++;
            }
        }catch (IOException e){
            System.out.println("Arquivo .txt nao encontrado! Verifique se ele se encontra dentro de Programa Leitura de Dados/src/Planilha PEDro Formatada");
        }
    }

    static void pontuacaoPorAno() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int anoPesquisa;
        System.out.println("\nDigite o ano em que deseja ver as notas:");
        anoPesquisa = sc.nextInt();

        int k = 1;
        int aux = 0;
        for (Dados dados : PEDro) {
            if (anoPesquisa == dados.ano) {
                System.out.println("Nota " + k + ": " + dados.score + "/10");
                k++;
                aux++;
            }
        }
        if(aux==0)
            System.out.println("\nO ano pesquisado nao possui nenhum dado...");

        TimeUnit.SECONDS.sleep(1);
    }

    static void terapiaPorAno() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Terapias registradas:");
        for (String s : terapia) {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(s);
        }

        String pesquisa = null;
        System.out.println("\nDigite qual terapia voce deseja buscar:");
        pesquisa = sc.nextLine();

        int[] anosAux = new int[65];
        int[] contadorAux = new int[65];
        int volatil = 1958;
        for(int i=0; i < 65; i++){
            anosAux[i] = volatil;
            contadorAux[i] = 0;
            volatil++;
        }


        int aux = 0;
        for(int i=0; i< PEDro.length; i++){
            if(PEDro[i].terapia.toUpperCase().contains(pesquisa.toUpperCase())){               //TODO: mudar metodo de pesquisa, pois esse vai dar erro; vai sempre encontrar um "terapia"
                for(int l = 0; l < anosAux.length; l++){
                    if(PEDro[i].ano == anosAux[l]){
                        contadorAux[l]++;
                        aux++;
                    }
                }
            }
        }

        if(aux==0){
            System.out.println("Nao houve nenhum ano com a terapia pesquisada. Certifique-se que foi escrita corretamente");
            TimeUnit.SECONDS.sleep(2);
        }
        else{
            System.out.println("Houveram " + aux + " resultados.\nExibindo...");
            TimeUnit.SECONDS.sleep(2);
            for(int i=0; i < 65; i++){
                if(contadorAux[i] >= 1){
                    System.out.println("Ano: " + anosAux[i] + "; Incidencia: " + contadorAux[i]);
                }
            }
        }
    }

    static void problemaPorAno() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Problemas registrados:");
        for (String s : problemas) {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(s);
        }

        String pesquisa = null;
        System.out.println("\nDigite qual problema voce deseja buscar:");
        pesquisa = sc.nextLine();

        int[] anosAux = new int[65];
        int[] contadorAux = new int[65];
        int volatil = 1958;
        for(int i=0; i < 65; i++){
            anosAux[i] = volatil;
            contadorAux[i] = 0;
            volatil++;
        }


        int aux = 0;
        for(int i=0; i< PEDro.length; i++){
            if(PEDro[i].problema.toUpperCase().contains(pesquisa.toUpperCase())){               //TODO: mudar metodo de pesquisa, pois esse vai dar erro; vai sempre encontrar um "terapia"
                for(int l = 0; l < anosAux.length; l++){
                    if(PEDro[i].ano == anosAux[l]){
                        contadorAux[l]++;
                        aux++;
                    }
                }
            }
        }

        if(aux==0){
            System.out.println("Nao houve nenhum ano com o problema pesquisado. Certifique-se que foi escrito corretamente");
            TimeUnit.SECONDS.sleep(2);
        }
        else{
            System.out.println("Houveram " + aux + " resultados.\nExibindo...");
            TimeUnit.SECONDS.sleep(2);
            for(int i=0; i < 65; i++){
                if(contadorAux[i] >= 1){
                    System.out.println("Ano: " + anosAux[i] + "; Incidencia: " + contadorAux[i]);
                }
            }
        }
    }

    static void corpoPorAno() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Partes do corpo registradas:");
        for (String s : corpo) {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(s);
        }

        String pesquisa = null;
        System.out.println("\nDigite qual parte do corpo voce deseja buscar:");
        pesquisa = sc.nextLine();

        int[] anosAux = new int[65];
        int[] contadorAux = new int[65];
        int volatil = 1958;
        for(int i=0; i < 65; i++){
            anosAux[i] = volatil;
            contadorAux[i] = 0;
            volatil++;
        }


        int aux = 0;
        for(int i=0; i< PEDro.length; i++){
            if(PEDro[i].parteCorpo.toUpperCase().contains(pesquisa.toUpperCase())){               //TODO: mudar metodo de pesquisa, pois esse vai dar erro; vai sempre encontrar um "terapia"
                for(int l = 0; l < anosAux.length; l++){
                    if(PEDro[i].ano == anosAux[l]){
                        contadorAux[l]++;
                        aux++;
                    }
                }
            }
        }

        if(aux==0){
            System.out.println("Nao houve nenhum ano com a Parte do Corpo pesquisada. Certifique-se que foi escrita corretamente");
            TimeUnit.SECONDS.sleep(2);
        }
        else{
            System.out.println("Houveram " + aux + " resultados.\nExibindo...");
            TimeUnit.SECONDS.sleep(2);
            for(int i=0; i < 65; i++){
                if(contadorAux[i] >= 1){
                    System.out.println("Ano: " + anosAux[i] + "; Incidencia: " + contadorAux[i]);
                }
            }
        }
    }

    static void mediaPontuacao(){
        float[] anosAux = new float[65];
        for(int i=0; i < anosAux.length; i++){
            anosAux[i] = 0;
        }
        int contador = 1958;
        int quantidade = 0;
        for(int i=0; i<anosAux.length; i++){
            for(int k=0; k < PEDro.length; k++){
                if(contador == PEDro[k].ano){
                    anosAux[i] += PEDro[k].score;
                    quantidade++;
                }
            }
            contador++;
            anosAux[i] = anosAux[i] / quantidade;
            quantidade = 0;
        }

        System.out.println("\nPontuacao media de cada ano:");
        contador = 1958;
        for(int i=0; i< anosAux.length; i++){
            System.out.println("Ano: " + contador + "; Pontuacao Media: " + anosAux[i]);
            contador++;
        }
    }

    static int menu(){
        Scanner sc = new Scanner(System.in);
        int escolha;
        System.out.println("\nEscolha uma das opcoes para prosseguir:");
        System.out.println("[1] - Pontuacao por Ano");
        System.out.println("[2] - Incidencia de Terapia por ano");
        System.out.println("[3] - Incidencia de Problema por ano");
        System.out.println("[4] - Incidencia de Parte do Corpo por ano");
        System.out.println("[5] - Media de pontuacao por ano");
        System.out.println("[0] - Fechar Programa");
        escolha = sc.nextInt();
        return escolha;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Comecando a leitura de arquivos...");
        for(int i=0; i < PEDro.length; i++){
            PEDro[i] = new Dados();
        }
        lerDados();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Quantidade de dados lidos: " + contadorGlobal + "\n");
        int op = 10;
        while(op!=0){
            op = menu();
            switch(op) {
                case 1:
                    pontuacaoPorAno();
                    break;
                case 2:
                    terapiaPorAno();
                    break;
                case 3:
                    problemaPorAno();
                    break;
                case 4:
                    corpoPorAno();
                    break;
                case 5:
                    mediaPontuacao();
                    break;
                default:
                    if (op != 0){
                        System.out.println("Digite uma das opcoes exibidas, somente com seu numero correspondente");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    break;
            }
        }
        System.out.println("Feito com todo carinho <3");
        TimeUnit.SECONDS.sleep(1);
    }
}