package br.com.ailacard;

import br.com.ailacard.cardmain.Card;
import br.com.ailacard.cardmain.Shopping;
import br.com.ailacard.cards.Black;
import br.com.ailacard.cards.Gold;
import br.com.ailacard.cards.Platinum;

import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome: ");
        String name = scanner.nextLine();

        Card selectedCard = null;

        while (true) { // Loop para garantir que escolha um cartão válido
            System.out.println("Olá " + name + "! Digite o número correspondente ao seu cartão:");
            System.out.println("1 - Gold");
            System.out.println("2 - Platinum");
            System.out.println("3 - Black");

            int cardSelect = scanner.nextInt();

            switch (cardSelect) {
                case 1:
                    selectedCard = new Gold();
                    System.out.println("Cartão Gold com limite de R$" + selectedCard.getLimit());
                    break;
                case 2:
                    selectedCard = new Platinum();
                    System.out.println("Cartão Platinum com limite de R$" + selectedCard.getLimit());
                    break;
                case 3:
                    selectedCard = new Black();
                    System.out.println("Cartão Black com limite de R$" + selectedCard.getLimit());
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
                    continue; // Volta para o início do loop
            }

            if (selectedCard != null) {
                break; // Sai do loop quando um cartão for escolhido
            }
        }

        int exit = 1;
        while (exit == 1) { // Loop de compras
            System.out.println("Digite o nome do produto que irá comprar: ");
            scanner.nextLine();
            String product = scanner.nextLine();

            //evitando quebrar o programa se o usuário digitar uma String no local do preço
            double price = 0;
            boolean validPrice= false;

            while (!validPrice) {
                System.out.println("Qual o valor? ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price > 0) {
                        validPrice = true;
                    } else {
                        System.out.println("Por favor! Insira um valor maior que zero");
                    }
                } else {
                    System.out.println("Inválido! Insira um número");
                    scanner.next();
                }
            }

            Shopping purchase = new Shopping(product, price);
            boolean makePurchase = selectedCard.makeShop(purchase);

            if (makePurchase) {
                System.out.println("Compra realizada com sucesso");
                System.out.println("Saldo R$" + selectedCard.getBalance());
            } else {
                System.out.println("Inválido. Saldo atual R$" + selectedCard.getBalance());
            }

            while (true) {
                // Verificar se o usuário deseja continuar
                System.out.println("Digite 1 para continuar comprando | Digite 0 para sair!");
                int continueChoice = scanner.nextInt();

                if (continueChoice == 1) {
                    break;
                } else if (continueChoice == 0) {
                    exit = 0;
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite 1 para continuar ou 0 para sair.");
                }
            }
        }
        if (!selectedCard.getPurchases().isEmpty()) {
            System.out.println("---Lista de Compras---");
            Collections.sort(selectedCard.getPurchases());

            double totalSpend = 0.0;

            for (Shopping s : selectedCard.getPurchases()) {
                System.out.println(s.getProduct() + " - " + s.getPrice());
                totalSpend += s.getPrice();  //soma o preço de cada compra
            }
            System.out.println("-----------");
            System.out.println("Total gasto: " + totalSpend);
            System.out.println("Saldo final do cartão = R$" + selectedCard.getBalance());
        }
        scanner.close();
    }
}
