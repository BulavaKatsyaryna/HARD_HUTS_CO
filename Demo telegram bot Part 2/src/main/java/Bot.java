import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
//Что бот будет возвращать на наши сообщения
    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());       //Чат на который будем отвечать.
        sendMessage.setReplyToMessageId(message.getMessageId());     //Сообщение, на которое будем отвечать.
        sendMessage.setText(text);
        try{
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//Метод для обновления сообщений, ответов
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/start":
                    sendMsg(message, "Привет, я проведу тебя по гастрономическому Минску, скажи какое блюдо ты хочешь, и мы его найдем!");
                    break;
                case "Город":
                    sendMsg(message, "Выбери город");
                    break;
                case "Помощь":
                    sendMsg(message, "Напиши название блюда (Стейк, солянка, пельмени?). \n" +
                            "Только пиши правильно, без ошибок, и я постараюсь отыскать именно то, что ты хочешь!");
                            break;
                case "Настройки":
                    sendMsg(message, "Что будем настраивать?");
                    break;
                case "О проекте:":
                    sendMsg(message, "Сделали этот чудесный бот пятеро таких же чудесных людей: \nЕкатерина, Виктор, Александр, Олександра, Юрий. \nМы не собираемся останавливаться на достигнутом. \nСкоро мы сможем выдавать заведения отталкиваясь от твоего местоположения и отмечать их на карте, вызывать такси, показывать фотографии блюд и многое другое.");
                    break;

                case "Солянка":                                 //Эти: \n переносят строку
                    sendMsg(message, "Кафе Скиф. \nПр-т Независимости, 34. \n\nСолянка мясная 250/60/40. \nЦена: 7р.");
                    sendMsg(message, "Ресторан У Барысыча. \nУл. П.Бровки, 12. \n\nСолянка сборная мясная 300/25. \nЦена: 6р.");
                    sendMsg(message, "Кафе Комедия. \nПр-т Независимости, 117. \n\nСолянка «Домашняя» 250/45/15/2. \nЦена: 8р.");
                    sendMsg(message, "Ресто-бар У холостяка. \nПр-т Независимости, 78. \n\nСолянка сборная мясная 300/10/2/30. \nЦена: 7р.");
                    break;

                case "Стейк":
                    sendMsg(message, "Амстердам ТЦ Замок. \nПр-т Победителей, 65. \n\nСтейк из говядины 170/20/10. \nЦена: 24,20 р.");
                    sendMsg(message, "Кафе Берёзка. \nПр-т Независимости, 40. \n\nСтейк-рибай с перечным соусом 520. \nЦена: 45р.");
                    sendMsg(message, "Кафе ПабЕда. \nПр-т газеты Правда 40/1. \n\nСтейк из куриного филе с соусом «Дор-блю» и картофелем «По‑деревенски» 150/100/50. \nЦена: 14р. \n\nСтейк «Миньон» с соусом «Деми гласс» и овощным гратеном 200/150/50. \nЦена: 24р.");
                    sendMsg(message, "Ресторан Свои. \nПр-т Независимости, 22. \n\nСтейк из говядины 200/50/50/1. \nЦена: 25.90р. \n\nСтейк из лосося 120/110/2. \nЦена: 25.90р.");
                    break;

                case "Пельмени":
                    sendMsg(message, "Амстердам ТЦ Замок. \nПр-т Победителей, 65. \n\nПельмени в соусе 310. \nЦена: 6.50р.");
                    sendMsg(message, "Кафе Аўстэрыя Уршуля. \nУл. Энгельса, 7. \n\nПельмени с говядиной 230/20. \nЦена: 8р. \n\nПельмени с говядиной и свининой 230/20. \nЦена: 8р.");
                    sendMsg(message, "Кальянная NUAHULE. \nУл. Красная, 12. \n\nПельмени домашние жареные 200/50. \nЦена: 12р. \n\nПельмени домашние отварные 300/45. \nЦена: 12р.");
                    break;

                case "Карпаччо":
                    sendMsg(message, "Кафе-бар Время. \nУл. Могилевская, 39а. \n\nКарпаччо из говядины  100/20/10/10. \nЦена: 15р. \n\nКарпаччо из лосося 100/20/10/10. \nЦена: 17р.");
                    sendMsg(message, "Кафе У Янки. \nУл. К. Маркса, 21. \n\nКарпаччо «Браво» 200. \nЦена: 9.50р.");
                    sendMsg(message, "Ресто-бар У холостяка. \nПр-т Независимости, 78. \n\nКарпаччо из говяжьей вырезки 100/20/30/. \nЦена: 17.90р.");
                    break;

                case "Драники":
                    sendMsg(message, "Кафе-бар Время. \nУл. Могилевская, 39а. \n\nДраники с курицей 150/130. \nЦена: 11р.");
                    sendMsg(message, "Паб 1067. \nУл. Володарского, 22. \n\nДранiкi са смажанымi вушамi i яйкамi 380. \nЦена: 7.30р. \n\nДранiкi з ялавiчнымi шчокамi i яйкамi 400. \nЦена: 8.50р. \n\nДранiкi з шампiньёнамi i яйкамi 380. \nЦена: 7.30р.");
                    sendMsg(message, "Ресторан Свои. \nПр-т Независимости, 22. \n\nДраники с лососем 200/100/30. \nЦена: 12.90р. \n\nДраники с грибами в сметанном соусе 200/140/15/3. \nЦена: 10р.");
                    sendMsg(message, "Мята Lounge. \nУл. Революционная, 10. \n\nДраники с копчёным лососем и сметаной 180/50/40/2. \nЦена: 15р.");
                    sendMsg(message, "Амстердам ТЦ Замок. \nПр-т Победителей, 65. \n\nДраник запечённый с птицей и грибами 250/65/5. \nЦена: 8.10р");

                    break;

                default:
            }
        }

    }


//Кнопки на клаве делаем
    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();        //Создаем клавиатуру
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);                  //Параметр, который выводит клавиатуру определенным пользователям или всем, на выбор
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);           //Скрывать клавиатуру или нет

//Создаем кнопки
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRowFirstRow = new KeyboardRow();                 //Инициализация строки
        keyboardRowFirstRow.add(new KeyboardButton("Город"));           //Создаем первую строку

        KeyboardRow keyboardRowSecondRow = new KeyboardRow();
        keyboardRowSecondRow.add(new KeyboardButton("Помощь"));
        keyboardRowSecondRow.add(new KeyboardButton("О проекте:"));

        KeyboardRow keyboardRowThirdRow = new KeyboardRow();
        keyboardRowThirdRow.add(new KeyboardButton("Настройки"));

        keyboardRowList.add(keyboardRowFirstRow);        //Добавляем строки клавиатуры в список
        keyboardRowList.add(keyboardRowSecondRow);       //Добавляем строки клавиатуры в список
        keyboardRowList.add(keyboardRowThirdRow);        //Добавляем строки клавиатуры в список
        replyKeyboardMarkup.setKeyboard(keyboardRowList);       //Устанавливаем список на клавиатуру

    }

    @Override
    public String getBotUsername() {
        return "HW_KateBulova_Bot";
    }

    @Override
    public String getBotToken() {
        return "1537025339:AAEkLWge9lc_d87S8aGaVxl-SgzENSiYe6A";
    }

}
