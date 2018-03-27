package view;

import javax.sound.sampled.*;               // Provides interfaces and classes for capture, processing, and playback of sampled audio data.
import java.io.File;                        // An abstract representation of file and directory pathnames.
import java.io.IOException;                 // Signals that an I/O exception of some sort has occurred.
import javax.sound.sampled.LineEvent.Type;  // The LineEvent.Type inner class identifies what kind of event occurred on a line. Static instances are provided for the common types (OPEN, CLOSE, START, and STOP).

/* classdoc
Provides the music to be played in the application. 
This class inherits from the Thread class. Meaning that the music plays in a separate thread.
A thread is a lightweight process that runs on the java virtual machine.

The class has two public methods.
- public void run()
- public void cancel()

One necessary method that needs to be defined when inheriting from a thread is the public void run() method. 
This method is called when one calls the `start()` method on an instance of this class. 
This method opens a .wav file calls the `playClip()` method giving in that file.

Additionally the Music class has another public method, 
called `public void cancel()` this interrupts the playback and lets the thread terminate.


This class has one private methods:
- private void playClip(File clipFile)

The playClip method stems almost with no changes from [PlaySoundFile]. 

It first defines another class, that implements the LineListener interface. 
This class is responsible for canceling the music if somebody wants to stop or cancel it.
Meaning that it does nothing until an update event occurs.

Then a listener is instantiated and additionally an input stream. This converts the .wav file into frames.

Next a clip object is created, this object can actually play the input stream provided by the audioInputStream object.
The listener is added to the clip. Lastly the clip is set to loop over the music.

Then the clip is actually played by calling `clip.start()`.


Note:
This class plays a prepared music file and is used before and after the level. 
The sheet music to the music is accessible in the resources/Soundtrack directory as a museScore (.mscz) file.
Not to be mistaken by the sound played in the level. That sound is provided in the logic.Sound class.
*/

public class Music extends Thread {
    public void run() {
        File file = new File("./resources/Soundtrack/Eartrainer.wav");
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
    }
    public void cancel() {
        interrupt();
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
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Custom change: Make the clip play in a loop.
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