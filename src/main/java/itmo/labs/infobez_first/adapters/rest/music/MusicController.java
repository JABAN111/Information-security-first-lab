package itmo.labs.infobez_first.adapters.rest.music;

import itmo.labs.infobez_first.adapters.rest.util.dto.MusicDto;
import itmo.labs.infobez_first.adapters.rest.util.mappers.MusicMapper;
import itmo.labs.infobez_first.core.ports.MusicService;
import lombok.AllArgsConstructor;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@AllArgsConstructor
public class MusicController {

    private MusicService musicService;

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> getMusic(@PathVariable Long id) {
        return ResponseEntity.ok(MusicMapper.toDto(musicService.getMusic(id)));
    }

    @GetMapping("/data")
    public ResponseEntity<List<MusicDto>> getMusicData() {
        return ResponseEntity.ok(MusicMapper.toDto(musicService.getMusics()));
    }

    @PostMapping
    public void addMusic(@RequestBody MusicDto musicDto) {
        musicDto.setAuthor(StringEscapeUtils.escapeHtml4(musicDto.getAuthor()));
        musicDto.setDescription(StringEscapeUtils.escapeHtml4(musicDto.getDescription()));
        musicService.add(MusicMapper.toEntity(musicDto));
    }
}
