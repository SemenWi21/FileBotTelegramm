package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;


public class FileBot extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new FileBot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Prosto_2_HoroshiyBot";
    }

    @Override
    public String getBotToken() {
        return "5367382431:AAFQGx2nr6E9BW5JFIuAOsfYgTE5rhwgTzg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()){
            if (message != null && message.hasText()){
                switch (message.getText()){
                    case "/help":
                        sendMsg(message, "Хай, видимо тебе нужно конвертнуть свой файл в другой формат, тогда ты зашел по адресу"
                                + "\n" + "\n" + "Самое главное правильно соблюдай последовательность:" + "\n" + "*1) Отправь файл*" + "\n" +
                                "*2) Выбери формат файла, который хочешь получить*" + "\n" + "\n" + "Ну вот и все пупсик пользуйся на здоровье");
                }
            }
        }else if(message.hasDocument()){
            try {
                GetFileBot.getFileFromBot("5367382431:AAFQGx2nr6E9BW5JFIuAOsfYgTE5rhwgTzg", message.getDocument().getFileId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String a = "l";
        }

    }
}
