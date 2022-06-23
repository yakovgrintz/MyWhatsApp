public class UpdateData extends Thread implements MyApp{
    public UpdateData(ListOfConatants list,AppPanel panel){
        new Thread(()->{
            while (true){
                try {
                    //DRIVER.checkAnswerAndStatus(list);
                    //panel.updateTable();
                    Thread.sleep(15*1000);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                }
            }
        });
    }
}
