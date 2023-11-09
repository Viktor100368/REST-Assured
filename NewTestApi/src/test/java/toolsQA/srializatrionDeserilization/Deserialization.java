package toolsQA.srializatrionDeserilization;

import java.io.*;

public class Deserialization {
   public static Object deserializeToObject(String filename){
       try(ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream(filename))){
                    return objectOutputStream.readObject();
       }catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }

    public static void main(String[] args) {
        Rectangle rec = (Rectangle) deserializeToObject("rectSerialize.bin");
        System.out.println(rec);
    }
}
