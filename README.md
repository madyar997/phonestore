# phonestore
Java EE Web application written using JSP and Servlet. Supports multilanguage. 

Инструкция по настройке окружения для запуска проекта PhoneStore
*Примечание: Проект был написан в Intellij IDEA, использовались JDK 1.8, Tomcat Server v. 9.0.41, СУБД MySQL и Workbench 8.0.22, Java Connector, поэтому для развертывания окружения понадобятся все данные инструменты

Для запуска приложения необходимо выполнить следующие шаги:
1)	После запуска Intellij IDEA в меню VCS выберите Checkout from version control и затем Git. В открывшемся окне вставьте ссылку на репозиторий для клонирования проекта.

2)	Создайте нового пользователя madyar@localhost пароль : madyar.
Для этого воспользуйтесь MySQL  Command Line Client и выполните следующие запросы:
CREATE USER 'madyar'@'localhost' IDENTIFIED BY 'mdamdamda'
CREATE DATABASE IF NOT EXISTS phonestore;
GRANT ALL ON phonestore.* TO 'madyar'@'localhost'
FLUSH PRIVILEGES

3)	После успешного создания пользователя, и передачи ему необходимых прав, перейдите в MySQL WorkBench.
3.1) Создайте новое соединение, укажите имя соединения и имя пользователя, введите пароль
3.2) При успешно созданном подключении, новое подключение появится на экране выберите его.
Готово. Соединение настроено

4)	Теперь необходимо создать таблицы и заполнить их данными, необходимыми и достаточными для демонстрации функционала приложения. 
В корне папки проекта лежит файл DBCreationScript.sql, откройте его, скопируйте содержимое этого файла в редактор MySQL. 
Нажмите на знак молнии для запуска скрипта.

5)	Теперь нужно подключить сервер Apache Tomcat. Для этого в правом верхнем углу нажмите “Add configuration”. В появившемся окне выберите Tomcat. Далее укажите путь к папке с распокованным архивом Tomcat
 
6)	Далее перейдите в пункт edit configuration. 
на вкладе server укажите следующий url: https://localhost:8080/phonestore
Перейдите на вкладу Deployment создайте артефакт нажав на знак "+" в ниней части экрана. Выберите war exploded. В поле Apllication context оставьте только /.
 
7)	Для ведения логирования укажите абсолютный путь файла log4j.properties хранящийся в папке resources в поле Constants.LOG_PROPERTIES_FILE
 
8)	После этого можно запускать приложение :) 

9)	По завершении работы с приложением, рекомендуется удалить пользователя и саму БД.
Для этого выполните следующие команды:
DROP USER 'madyar'@'localhost'
DROP DATABASE IF EXISTS phonestore

