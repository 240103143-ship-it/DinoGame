private void showGameOver() {
        Label over = new Label("GAME OVER\n\nPRESS R TO RESTART");
        over.setStyle(
                "-fx-font-family: 'Press Start 2P';" +
                        "-fx-font-size: 18px;" +
                        "-fx-text-fill: black;" +
                        "-fx-alignment: center;"
        );

        over.setPrefWidth(WIDTH);
        over.setLayoutY(HEIGHT / 2 - 40);
        over.setAlignment(javafx.geometry.Pos.CENTER);

        gamePane.getChildren().add(over);
        over.toFront();
    }
}
\
