package com.os.comment.utils;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

/**
 * 获取mp3文件时长等信息
 * Created by PengSongHe on 2015/12/30.
 */
public class Mp3Utils {
    public static void method2(String path) {
        File file = new File(path);
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            System.out.println(audioHeader.getTrackLength());
            System.out.println(audioHeader.getFormat());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
         method2("d:/1.mp3");
    }
}
