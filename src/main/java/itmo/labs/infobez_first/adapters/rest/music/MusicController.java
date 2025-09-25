package itmo.labs.infobez_first.adapters.rest.music;

import itmo.labs.infobez_first.adapters.rest.util.dto.MusicDto;
import itmo.labs.infobez_first.adapters.rest.util.mappers.MusicMapper;
import itmo.labs.infobez_first.core.ports.MusicService;
import itmo.labs.infobez_first.core.ports.UserService;
import lombok.AllArgsConstructor;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/music")
@AllArgsConstructor
public class MusicController {
    private final Supplier<MusicService> musicService;

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> getMusic(@PathVariable Long id) {
        MusicService musicServ = musicService.get();
        return ResponseEntity.ok(MusicMapper.toDto(musicServ.getMusic(id)));
    }

    @GetMapping("/data")
    public ResponseEntity<List<MusicDto>> getMusicData() {
        MusicService musicServ = musicService.get();
        return ResponseEntity.ok(MusicMapper.toDto(musicServ.getMusics()));
    }

    @PostMapping
    public void addMusic(@RequestBody MusicDto musicDto) {
        MusicService musicServ = musicService.get();
        musicDto.setAuthor(StringEscapeUtils.escapeHtml4(musicDto.getAuthor()));
        musicDto.setDescription(StringEscapeUtils.escapeHtml4(musicDto.getDescription()));
        musicServ.add(MusicMapper.toEntity(musicDto));
    }
}
