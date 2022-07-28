package bot;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpConnection;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

public class GetFileBot {

    public static void getFileFromBot(String token, String fileId) throws IOException {
        URL url = new URL("https://api.telegram.org/bot" + token + "/getFile?file_id=" + fileId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream(), "UTF-8") {
        });
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine();
        }
        JSONObject jsonObject = new JSONObject(result);
        String filePath = jsonObject.getJSONArray("result").getJSONObject(3).getString("file_path");


        URL urlTwo = new URL("https://api.telegram.org/file/bot" + token + "/" + filePath);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOS = new FileOutputStream("C:\\Users\\qwert\\Desktop\\ПРОГРАММИРОВАНИЕ\\FolderForBot\\DOC.txt");
        FileChannel fileChannel = fileOS.getChannel();
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }
}
