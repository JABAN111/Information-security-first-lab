package itmo.labs.infobez_first.adapters.rest.util.mappers;

import itmo.labs.infobez_first.adapters.rest.util.dto.MusicDto;
import itmo.labs.infobez_first.core.entity.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicMapper {
    public static Music toEntity(MusicDto musicDto) {
        Music music = new Music();
        music.setTitle(musicDto.getTitle());
        music.setDescription(musicDto.getDescription());
        music.setAuthor(musicDto.getAuthor());
        return music;
    }
    public static MusicDto toDto(Music music) {
        MusicDto musicDto = new MusicDto();
        musicDto.setId(music.getId());
        musicDto.setTitle(music.getTitle());
        musicDto.setDescription(music.getDescription());
        musicDto.setAuthor(music.getAuthor());
        return musicDto;
    }

    public static List<MusicDto> toDto(List<Music> musics) {
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Music music : musics) {
            musicDtos.add(toDto(music));
        }
        return musicDtos;
    }
}
