package view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class Music extends Thread {
    public void run() {
        play();
    }
    public void cancel() {
        interrupt();
    }

    private void play() {
        File file = new File("./resources/sound/example.wav");
        try {
            playClip(file);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch(LineUnavailableException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Something went wrong.");
        }
    }

    private void playClip(File clipFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override 
            public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }
        
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
        try {
            clip.start();
            listener.waitUntilDone();
        } finally {
            clip.close();
        }
        } finally {
            audioInputStream.close();
        }
    }

}