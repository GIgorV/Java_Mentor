package Core_4.test4_3_7;/*
Эта задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться и
написать объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.

Дан набор классов, описывающих работу гипотетической почтовой системы.
Для начала рассмотрим код, описывающий все используемые сущности.

Интерфейс Core_4.test4_3_7.Sendable:
сущность, которую можно отправить по почте. У такой сущности можно получить от кого и кому направляется письмо.

У Core_4.test4_3_7.Sendable есть два наследника, объединенные следующим абстрактным классом:
Абстрактный класс Core_4.test4_3_7.AbstractSendable ,который позволяет абстрагировать логику хранения источника и получателя письма
в соответствующих полях класса.

Первый класс Core_4.test4_3_7.MailMessage описывает обычное письмо, в котором находится только текстовое сообщение:
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
Второй класс Core_4.test4_3_7.MailPackage описывает почтовую посылку:
Посылка, содержимое которой можно получить с помощью метода `getContent`
При этом сама посылка описывается классом Core_4.test4_3_7.Package:
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.

Теперь рассмотрим классы, которые моделируют работу почтового сервиса:
Интерфейс Core_4.test4_3_7.MailService, который задает класс, который может каким-либо образом обработать почтовый объект.
Класс Core_4.test4_3_7.RealMailService, в котором скрыта логика настоящей почты.

Вам необходимо описать набор классов, каждый из которых является Core_4.test4_3_7.MailService:

1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц,
а затем, в конце концов, передает получившийся объект непосредственно экземпляру Core_4.test4_3_7.RealMailService.
У UntrustworthyMailWorker должен быть конструктор от массива Core_4.test4_3_7.MailService
(результат вызова processMail первого элемента массива передается на вход processMail второго элемента, и т. д.)
и метод getRealMailService, который возвращает ссылку на внутренний экземпляр Core_4.test4_3_7.RealMailService,
он не приходит масивом в конструкторе и не настраивается извне класса.

2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.
Он следит только за объектами класса Core_4.test4_3_7.MailMessage и пишет в логгер следующие сообщения (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}

3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное.
Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать.
Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок,
которые он своровал.
Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую,
такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".

4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
если была обнаружена подобная посылка.
Если он заметил запрещенную посылку с одним из запрещенных содержимым ("weapons" и "banned substance"),
то он бросает IllegalPackageException.
Если он находит посылку, состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException.
Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.

Все классы должны быть определены как публичные и статические,
так как в процессе проверки ваш код будет подставлен во внешний класс, который занимается тестированием и проверкой структуры.
Для удобства во внешнем классе объявлено несколько удобных констант и импортировано все содержимое пакета java.util.logging.
Для определения, посылкой или письмом является Core_4.test4_3_7.Sendable объект воспользуйтесь оператором instanceof.

public static final String AUSTIN_POWERS = "Austin Powers";
public static final String WEAPONS = "weapons";
public static final String BANNED_SUBSTANCE = "banned substance";
 */

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    //Stepik code: start

    public static class Spy implements MailService {
        final Logger LOGGER;

        public Spy(Logger logger) {
            this.LOGGER = logger;
        }

        //Spy – здесь и далее становится важным подгон Core_4.test4_3_7.Sendable к типам наследников
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom().contains(AUSTIN_POWERS) ||
                        mail.getTo().contains(AUSTIN_POWERS)) {
                    //как кавычки правильно экранировать?
                    LOGGER.log(Level.WARNING,
                            "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
                } else {
                    LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[]{mail.getFrom(), mail.getTo()});
                }
            } else return mail;
            return mail;
        }
    }

    public static class Thief implements MailService {
        int minPrice;
        int stolenValue = 0;

        public Thief(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage && ((MailPackage) mail).getContent().getPrice() >= minPrice) {
                Package newMail = new Package("stones instead of "
                        + ((MailPackage) mail).getContent().getContent(), 0);
                stolenValue += ((MailPackage) mail).getContent().getPrice();
                return new MailPackage(mail.getFrom(), mail.getTo(), newMail);
            } else return mail;
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                if (((MailPackage) mail).getContent().getContent().contains(BANNED_SUBSTANCE) ||
                        ((MailPackage) mail).getContent().getContent().contains(WEAPONS)) {
                    throw new IllegalPackageException();
                }
                if (((MailPackage) mail).getContent().getContent().contains("stones"))
                    throw new StolenPackageException();
            } else return mail;
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
    }

    public static class StolenPackageException extends RuntimeException {
    }

    public static class UntrustworthyMailWorker implements MailService {
        private final RealMailService realMailService;
        private final MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            this.realMailService = new RealMailService();
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable temp = mail;
            for (MailService mailService : mailServices) {
                temp = mailService.processMail(temp);
            }
            return realMailService.processMail(temp);
        }
    }
/*
Николай: Немного метод переделай у работника - создай внутри метода переменную
и записывай в нее результат вызова processMail для каждого mailService
Что тебе вернет mailServices.processMail(mail) ?
Ну вот как я писал его ранее - создай объект класса Core_4.test4_3_7.Sendable и записывай в него результат вызова метода process mail
Core_4.test4_3_7.Sendable temp не должен быть null, в processMail надо передавать не mail, а tmp
 */
//Данный метод должен последовательно передавать из рук в руки отправление по переданному списку,
// и вернуть результат работы Core_4.test4_3_7.RealMailService (который, должен быть создан вами же)

//        @Override
//        public Core_4.test4_3_7.Sendable processMail(Core_4.test4_3_7.Sendable mail) {
//            for (Core_4.test4_3_7.MailService mailService : mailServices) {
//                mailService.processMail(mail);
//            }
//            return realMailService.processMail(mail);
//        }


    //Stepik code: end
/*
Проблема была в неверно работающем классе UntrustworthyMailWorker.
Для проверки дайте на вход ему массив из объектов классов Thief и Inspector.
А в посылке должно лежать что-то, что будет украдено.

У Spy в логах с WARN должны быть кавычки вокруг message (строка точно как в условии).
В строке лога, который пишет Spy присутствуют фигурные скобки,
а класс Thief подменяет содержимое на строки, в которых эти скобки присутствовать не должны- не очевидные детали.

Сравнивать объекты на равенство лучше всего через метод объекта, который не может равнятся null.
Это помогает избегать неожиданных NullPointerException. Если оба объекта могут быть null, может помочь Objects.equals
 */


    /*
Класс, в котором скрыта логика настоящей почты
*/
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(10000);
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(new MailService[]{spy, thief, inspector});
        AbstractSendable[] correspondence = {
                new MailMessage("Oxxxymiron", "Гнойный", " Я здесь чисто по фану, поглумиться над слабым\n" +
                        "Ты же вылез из мамы под мой дисс на Бабана...."),
                new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                        "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                new MailMessage("Жириновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                new MailPackage("берег", "море", new Package("ВВЖ", 32)),
                new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("ракетный двигатель ", 2500000)),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones ", 1000)),
                new MailPackage("Китай", "КНДР", new Package("banned substance ", 10000)),
                new MailPackage(AUSTIN_POWERS, "Жопа запрещенная группировка", new Package("tiny bomb", 9000)),
                new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
        };

        for (AbstractSendable p : correspondence) {
            try {
                print("До:  ", p);
                Sendable sendable = worker.processMail(p);
                print("После:  ", sendable);
            } catch (StolenPackageException | IllegalPackageException e) {
                logger.log(Level.WARNING, "из: " + p.getFrom() + " куда: " + p.getTo() + " Содержимое: "
                        + (p instanceof MailMessage ? ((MailMessage) p).getMessage() : ((MailPackage) p).getContent().getContent()
                        + " Цена=" + ((MailPackage) p).getContent().getPrice()) + " Exceptions: " + e);
            }
        }
    }

    public static void print(String prefix, Sendable p) {
        System.out.println(prefix + "из: " + p.getFrom() + " куда: " + p.getTo() + " Содержимое: "
                + (p instanceof MailMessage ? ((MailMessage) p).getMessage() : ((MailPackage) p).getContent().getContent()
                + " Цена=" + ((MailPackage) p).getContent().getPrice()));
    }
}
