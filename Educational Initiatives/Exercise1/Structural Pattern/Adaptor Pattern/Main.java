interface MediaPlayer {
    void play(String audioType);
}

class AudioPlayer implements MediaPlayer {
    public void play(String audioType) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3");
        } else {
            System.out.println("Unsupported format");
        }
    }
}

class MediaAdapter implements MediaPlayer {
    private AudioPlayer audioPlayer;

    public MediaAdapter() {
        this.audioPlayer = new AudioPlayer();
    }

    @Override
    public void play(String audioType) {
        audioPlayer.play(audioType);
    }
}

public class Main {
    public static void main(String[] args) {
        MediaAdapter mediaAdapter = new MediaAdapter();
        mediaAdapter.play("mp3");  // Playing mp3
        mediaAdapter.play("wav");  // Unsupported format
    }
}
