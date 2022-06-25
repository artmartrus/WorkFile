import java.io.*;
import java.util.zip.*;


public class Main {
    public static void main(String[] args) {

        install();
        saveGame();
        zipper();

    }

    private static String createFile(String name) {

        File file = new File(name);
        try {
            if (file.createNewFile())
                System.out.println("Файл создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return name;

    }

    private static String createDir(String name) {

        File dir = new File(name);
        if (dir.mkdir())
            System.out.println("Каталог " + name + "создан");

        return name;
    }

    private static void deleteFile(String name) {

        File file = new File(name);
        try {
            if (file.delete())
                System.out.println("Файл создан");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipper() {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("Games/savegames/zip_save.zip"));
             FileInputStream fis1 = new FileInputStream("Games/savegames/save1.dat");
             FileInputStream fis2 = new FileInputStream("Games/savegames/save1.dat");
             FileInputStream fis3 = new FileInputStream("Games/savegames/save1.dat")) {

            ZipEntry entry1 = new ZipEntry("save1.dat");
            ZipEntry entry2 = new ZipEntry("save2.dat");
            ZipEntry entry3 = new ZipEntry("save3.dat");

            byte[] buffer = new byte[fis1.available()];

            zout.putNextEntry(entry1);
            fis1.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            zout.putNextEntry(entry2);
            fis2.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            zout.putNextEntry(entry3);
            fis3.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        deleteFile("Games/savegames/save1.dat");
        deleteFile("Games/savegames/save2.dat");
        deleteFile("Games/savegames/save3.dat");
    }

    private static void install() {

        String Dir1 = createDir("Games/src");
        String Dir2 = createDir("Games/res");
        String Dir3 = createDir("Games/savegames");
        String Dir4 = createDir("Games/temp");
        String Dir5 = createDir("Games/src/main");
        String Dir6 = createDir("Games/src/test");
        String File1 = createFile("Games/src/main/Main.java");
        String File2 = createFile("Games/src/main/Utils.java");
        String Dir7 = createDir("Games/res/drawables");
        String Dir8 = createDir("Games/res/vectors");
        String Dir9 = createDir("Games/res/icons");
        String File3 = createFile("Games/temp/temp.txt");

        StringBuilder sb = new StringBuilder();
        sb.append("Созданы: " + '\n' + Dir1 + '\n' + Dir2 + '\n' + Dir3 + '\n' + Dir4 + '\n' + Dir5 + '\n' +
                Dir6 + '\n' + Dir7 + '\n' + Dir8 + '\n' + Dir9 + '\n' + File1 + '\n' + File2 + '\n' + File3);
        String text = sb.toString();

        try (FileWriter writer = new FileWriter("Games/temp/temp.txt", true)) {
            writer.write(text);
            writer.append("\n");
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());


        }
    }

    private static void saveGame () {

        GameProgress gameProgress1 = new GameProgress(99, 100, 1, 234.8);
        GameProgress gameProgress2 = new GameProgress(76, 49, 2, 70.4);
        GameProgress gameProgress3 = new GameProgress(32, 20, 3, 99);

        save(gameProgress1, "Games/savegames/save1.dat");
        save(gameProgress2, "Games/savegames/save2.dat");
        save(gameProgress3, "Games/savegames/save3.dat");


    }

    private static void save (GameProgress name, String path){

        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(name);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
