package toolsQA.srializatrionDeserilization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
 public static void serializeToStream(Object classObject,String filename){
     //classObject - объект класса, который мы сериализуем(переводим в поток байтов), filename -
     // файл в который мы будем писоть поток бойтов
     try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
         objectOutputStream.writeObject(classObject);

     } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
     } catch (IOException e) {
         throw new RuntimeException(e);
     }
 }

    public static void main(String[] args) {
        Rectangle rec = new Rectangle(17,98);
        serializeToStream(rec,"rectSerialize.bin");
    }

}
