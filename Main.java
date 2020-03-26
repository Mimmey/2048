package com.company;
import java.util.*;

public class Main
{
    public static void sortToHall(short intArray[], String stringArray[]){ //sorting in decreasing order
        short last = (short)intArray.length;
        for (boolean sorted = last == 0; !sorted; last--) {
            sorted = true;
            for (short i = 1; i < last; i++)
            {
                if (intArray[i - 1] < intArray[i])
                {
                    sorted = false;
                    String tmp2 = stringArray[i - 1];
                    short tmp = intArray[i - 1];
                    intArray[i - 1] = intArray[i];
                    stringArray[i - 1] = stringArray[i];
                    intArray[i] = tmp;
                    stringArray[i] = tmp2;
                }
            }
        }
    }

    public static void mainMenu(){ //displays the main menu
        System.out.println("-------------------------------------------------------");
        System.out.println("Press 1 to begin a new game");
        System.out.println("Press 2 to load the game");
        System.out.println("Press 3 to learn the rules of game");
        System.out.println("Press 4 to go to the Hall of Fame");
        System.out.println("Press 5 to exit\n");
        System.out.print("Your choise is: ");
    }

    public static void writeField(short a[][], String name, short score[]){ //displays the playing field
        System.out.print(name + "     Score: " + score[0] + "\n");
        for (short i = 0; i < 4; i++) {
            for (short j = 0; j < 4; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nPress 0 to go to menu");
        System.out.println("-------------------------------------------------------");
    }

    public static void writeHall(String nameHall[], short scoreHall[]){ //displays five best saved positions in the format of the Hall of Fame
        short n;
        sortToHall(scoreHall, nameHall);
        for (short i = 0; i < 5; i++) {
            n = (short)(i + 1);
            System.out.println(n + ". " + nameHall[i] + " - " + scoreHall[i]);
        }
        System.out.println("");
    }

    public static short randomNumberIn(int min, int max){ //generates a random integer in the range from min to max
        max -= min;
        return (short)((Math.random() * ++max) + min);
    }

    public static void sortZeroesBegin(short a[]){ //sorts the array so that all zeros go to the beginning
        for (short i = 1; i < a.length; i++) {
            if(a[i] == 0){
                for (short j = i; j > 0; j--) {
                    short tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    public static void sortZeroesEnd(short a[]){ //sorts the array so that all zeros go to the end
        for(short i = 0; i < a.length - 1; i++){
            if(a[i] == 0){
                for(short j = i; j < a.length - 1; j++){
                    short tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    public static void moveDown(short ar[][], short reserveArray[], short score[]){ //action on the move down
        for(short i = 0; i < ar.length; i++){
            for(short j = 0; j < ar.length; j++){
                reserveArray[j] = ar[j][i];
            }
            sortZeroesBegin(reserveArray);
            for(short j = 0; j < ar.length; j++){
                ar[j][i] = reserveArray[j];
            }
        }

        for (short i = 0; i < ar.length; i++) {
            for(short j = (short)(ar.length - 2); j >= 0; j--){
                if(ar[j][i] == ar[j + 1][i]){
                    ar[j + 1][i] += ar[j][i];
                    score[0] += ar[j + 1][i];
                    ar[j][i] = 0;
                }
            }
        }
        for(short i = 0; i < ar.length; i++){
            for(short j = 0; j < ar.length; j++){
                reserveArray[j] = ar[j][i];
            }
            sortZeroesBegin(reserveArray);
            for(short j = 0; j < ar.length; j++){
                ar[j][i] = reserveArray[j];
            }
        }
    }

    public static void moveUp(short ar[][], short reserveArray[], short score[]){ //action on the move up
        for(short i = 0; i < ar.length; i++){
            for(short j = 0; j < ar.length; j++){
                reserveArray[j] = ar[j][i];
            }
            sortZeroesEnd(reserveArray);
            for(short j = 0; j < ar.length; j++){
                ar[j][i] = reserveArray[j];
            }
        }

        for (short i = 0; i < ar.length; i++) {
            for(short j = 0; j < ar.length - 1; j++){
                if(ar[j][i] == ar[j + 1][i]){
                    ar[j][i] += ar[j + 1][i];
                    score[0] += ar[j][i];
                    ar[j + 1][i] = 0;
                }
            }
        }

        for(short i = 0; i < ar.length; i++){
            for(short j = 0; j < ar.length; j++){
                reserveArray[j] = ar[j][i];
            }
            sortZeroesEnd(reserveArray);
            for(short j = 0; j < ar.length; j++){
                ar[j][i] = reserveArray[j];
            }
        }
    }

    public static void moveRight(short ar[][], short score[]){ //action on the move right
        for(short i = 0; i < ar.length; i++){
            sortZeroesBegin(ar[i]);
        }

        for (short i = 0; i < ar.length; i++) {
            for(short j = (short)(ar.length - 2); j >= 0; j--){
                if(ar[i][j] == ar[i][j + 1]){
                    ar[i][j + 1] += ar[i][j];
                    score[0] += ar[i][j + 1];
                    ar[i][j] = 0;
                }
            }
        }

        for(short i = 0; i < ar.length; i++){
            sortZeroesBegin(ar[i]);
        }
    }

    public static void moveLeft(short ar[][], short score[]){//action on the move left
        for(short i = 0; i < ar.length; i++){
            sortZeroesEnd(ar[i]);
        }

        for (short i = 0; i < ar.length; i++) {
            for(short j = 0; j < ar.length - 1; j++){
                if(ar[i][j] == ar[i][j + 1]){
                    ar[i][j] += ar[i][j + 1];
                    score[0] += ar[i][j];
                    ar[i][j + 1] = 0;
                }
            }
        }

        for(short i = 0; i < ar.length; i++){
            sortZeroesEnd(ar[i]);
        }
    }

    public static void generateTwoOrFourInRandomPlace(short a[][]){//in the random zero cell of the array generates two or four with a probability of 10: 1
        short ranTwoOrFour = randomNumberIn(1, 10);
        boolean four = (ranTwoOrFour == 10);
        short i = randomNumberIn(0, 3);
        short j = randomNumberIn(0, 3);
        while(a[i][j] != 0){
            i = randomNumberIn(0, 3);
            j = randomNumberIn(0, 3);
        }
        if(four)a[i][j] = 4;
        else a[i][j] = 2;
    }

    public static boolean zeroCheck(short a[][]){ //checks if there are zeros in the array
        boolean check = false;
        for (short i = 0; i < 4; i++) {
            for (short j = 0; j < 4; j++) {
                if(a[i][j] == 0){
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public static boolean winCheck(short a[][]){ //check if the player has defeated
        boolean win = false;

        for(short i = 0; i < a.length; i++){
            for (short j = 0; j < a[i].length; j++) {
                if(a[i][j] >= 2048){
                    win = true;
                    break;
                }
            }
        }
        return win;
    }

    public static boolean loseCheck(short a[][]){ //check if the player has lost
        boolean lose = true;
        for (short i = 0; i < 4; i++) {
            for (short j = 0; j < 3; j++) {
                if(a[i][j] == a[i][j + 1]){
                    lose = false;
                    break;
                }
            }
        }

        if(lose) {
            for (short i = 0; i < 4; i++) {
                for (short j = 0; j < 3; j++) {
                    if(a[j][i] == a[j + 1][i]){
                        lose = false;
                        break;
                    }
                }
            }
        }

        if(zeroCheck(a))lose = false;

        return lose;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String nameHall[] = new String[6];
        short reserveArray[] = new short[4];
        short scoreHall[] = new short[6];
        short field[][] = new short[4][4];
        short score[] = new short[1];
        short gamingChoise;
        nameHall[0] = "The Great Robodaddy";
        nameHall[1] = "Robonana the programmer";
        nameHall[2] = "ROBOBOBO";
        nameHall[3] = "Robobaby";
        nameHall[4] = "Robowarm";
        scoreHall[0] = 8192;
        scoreHall[1] = 1536;
        scoreHall[2] = 640;
        scoreHall[3] = 96;
        scoreHall[4] = 16;
        System.out.println("Enter your name, please.");
        System.out.print("Your name is: ");
        String name = in.nextLine();
        String nameCheck = "ROBOBOBO";
        if(name.toUpperCase().equals(nameCheck)){
            System.out.println("OH, HONEY... ARE YOU ADMIRE ME SO MUCH? GOD, ROBOBOBO IS GR-R-R-REAT LIKE HER FATHER! BUT YOU SHOULD BE YOURSELF AND CHOISE ANOTHER NAME.");
            System.out.println("Enter your name, please.");
            System.out.print("Your name is: ");
            name = in.nextLine();
            if(name.toUpperCase().equals(nameCheck)){

                System.out.println("HMM, YOU REALLY WANNA DO IT? OK, OK... OK...");
            }
        }
        nameHall[5] = name;
        System.out.println("-------------------------------------------------------");
        System.out.println("WELCOME TO 2048, " + name.toUpperCase() + "! I'M ROBOBOBO, THE KEEPER OF THIS GAME \nI WILL SHOW YOU THE GREATEST GAME IN MY ROBOWORLD!");
        mainMenu();
        short choise = in.nextShort();
        while(choise != -9999) {
            switch (choise) {
                case 1:
                    scoreHall[5] = score[0];
                    System.out.println("-------------------------------------------------------");
                    System.out.println("OH, I HOPE YOU'LL WIN AND EVEN GAIN ENOUGH POINTS TO GET TO THE HALL OF FAME!");
                    for (short i = 0; i < 4; i++) {
                        for (short j = 0; j < 4; j++) {
                            field[i][j] = 0;
                        }
                    }
                    generateTwoOrFourInRandomPlace(field);
                    choise++;
                    break;
                case 2:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("LET THE GAME BEGIN!");
                    writeField(field, name, score);
                    gamingChoise = 1;
                    while(!winCheck(field) && !loseCheck(field) && gamingChoise != 0) {
                        gamingChoise = in.nextShort();
                        switch (gamingChoise) {
                            case 2:
                                moveDown(field, reserveArray, score);
                                if (zeroCheck(field)) generateTwoOrFourInRandomPlace(field);
                                writeField(field, name, score);
                                break;
                            case 8:
                                moveUp(field, reserveArray, score);
                                if (zeroCheck(field)) generateTwoOrFourInRandomPlace(field);
                                writeField(field, name, score);
                                break;
                            case 6:
                                moveRight(field, score);
                                if (zeroCheck(field)) generateTwoOrFourInRandomPlace(field);
                                writeField(field, name, score);
                                break;
                            case 4:
                                moveLeft(field, score);
                                if (zeroCheck(field)) generateTwoOrFourInRandomPlace(field);
                                writeField(field, name, score);
                                break;
                            default:
                                break;
                        }
                    }
                    if(winCheck(field)){
                        writeField(field, name, score);
                        System.out.println("-------------------------------------------------------");
                        System.out.println("OH, BABE, YOU ARE THE WINNER! I'M SO-O-O PROUD IN YOU! LET'S GO TO THE HALL OF FAME!");
                        scoreHall[5] = score[0];
                        for (short i = 0; i < 4; i++) {
                            for (short j = 0; j < 4; j++) {
                                field[i][j] = 0;
                            }
                        }
                        break;
                    }

                    if(loseCheck(field)){
                        writeField(field, name, score);
                        System.out.println("-------------------------------------------------------");
                        System.out.println("OH, BABE, DON'T WORRY, YOU WILL WIN NEXT TIME! SO... LET'S GO TO THE HALL OF FAME, I'M INTERESTED IN YOUR RESULTS");
                        scoreHall[5] = score[0];
                        for (short i = 0; i < 4; i++) {
                            for (short j = 0; j < 4; j++) {
                                field[i][j] = 0;
                            }
                        }
                        break;
                    }
                    mainMenu();
                    choise = in.nextShort();
                    break;
                case 3:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("OH, DON'T YOU KNOW THE RULES, BABY? \nROBOBOBO CAN SHOW YOU!\n");
                    System.out.println("2048 IS PLAYED ON A 4 × 4 GRID, WITH NUMBERED TILES THAT SLIDE WHEN A PLAYER MOVES THEM USING FOUR KEYS; 2 (DOWN), 6 (RIGHT), 4 (LEFT) AND 8 (UP).");
                    System.out.println("EVERY TURN, A NEW TILE WILL RANDOMLY APPEAR IN AN EMPTY SPOT ON THE BOARD WITH A VALUE OF EITHER 2 OR 4. TILES SLIDE AS FAR AS POSSIBLE IN THE CHOSEN DIRECTION UNTIL THEY HAVE STOPPED BY EITHER ANOTHER TILE OR THE EDGE OF THE GRID.");
                    System.out.println("IF TWO TILES OF THE SAME NUMBER OF COLLIDE WHILE MOVING, THEY WILL MERGE INTO A TILE WITH THE TOTAL VALUE OF THE TWO TILES THAT COLLIDED. THE RESULTING TILE CAN NOT MERGE WITH ANOTHER TILE AGAIN IN THE SAME MOVE.");
                    System.out.println("A SCOREBOARD ON THE UPPER-RIGHT TRACK OF THE USER'S SCORE. THE USER'S SCORE STARTS AT ZERO, AND IS INCREMENTED WHEN TWO TILES COMBINE, BY THE VALUE OF THE NEW TILE.\n");
                    System.out.println("HAVE YOU LEARNED EVERYTHING, BABE? GR-R-R-REATFULLY, BABE! YOU SHOULD START THE GAME!\n");
                    mainMenu();
                    choise = in.nextShort();
                    break;
                case 4:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("BABE, DO YOU WANNA KNOW THE NAMES OF THE GR-R-R-REATEST PLAYERS OF 2048? SO, THIS IS THE HALL OF FAME!\n");
                    writeHall(nameHall, scoreHall);
                    System.out.println("IT'S ALL, BABE? GR-R-R-REATFULLY! YOU SHOULD START THE GAME!\n");
                    mainMenu();
                    choise = in.nextShort();
                    break;
                case 5:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("OH, BABY, DO YOU REALLY WANNA EXIT? YES? SURE. HOODY-GOODY-BYE!");
                    choise = -9999;
                    break;
                default:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("BABE, YOU HAVE JUST CRASHED ALL THE GAME, BUT KIND ROBOBOBO HAVE SAVED IT! I THINK, YOU WANTED TO... HMM... TO EXIT? YES, EXIT. HOODY-GOODY-BYE, SWEETIE!");
                    choise = -9999;
                    break;
            }
        }
    }
}