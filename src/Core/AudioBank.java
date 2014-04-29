package Core;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created by Ollie on 28/04/14.
 */
public class AudioBank {

    public static Audio Hit1;
    public static Audio Hit2;
    public static Audio Pickup1;
    public static Audio Select1;
    public static Audio Select2;

    public static void init(){
        try {
            Hit1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/Hit1.wav"));
            Hit2 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/Hit2.wav"));
            Pickup1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/Pickup1.wav"));
            Select1 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/Select1.wav"));
            Select2 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/Select2.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playEffect(Audio effect){
        effect.playAsSoundEffect(1, 1, false);
    }

}
