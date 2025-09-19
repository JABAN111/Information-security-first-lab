package itmo.labs.infobez_first.adapters.music;

import itmo.labs.infobez_first.core.entity.Music;
import itmo.labs.infobez_first.core.exceptions.NotFoundException;
import itmo.labs.infobez_first.core.ports.MusicRepository;
import itmo.labs.infobez_first.core.ports.MusicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MusicServiceImpl implements MusicService {

    private MusicRepository repository;

    @Override
    public Music getMusic(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("music with id " + id + " not found"));
    }

    @Override
    public List<Music> getMusics() {
        return repository.findAll();
    }

    @Override
    public void add(Music music) {
        if (music == null) {
            throw new IllegalArgumentException("music cannot be null");
        }
        if (music.getDescription() == null
                || music.getTitle() == null
                || music.getAuthor() == null
        ) {
            throw new IllegalArgumentException("music description cannot be null");
        }
        repository.save(music);
    }
}
