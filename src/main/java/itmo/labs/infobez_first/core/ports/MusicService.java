package itmo.labs.infobez_first.core.ports;

import itmo.labs.infobez_first.core.entity.Music;

import java.util.List;

public interface MusicService {
    Music getMusic(Long id);
    List<Music> getMusics();
    void add(Music music);
}
