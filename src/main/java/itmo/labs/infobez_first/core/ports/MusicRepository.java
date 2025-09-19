package itmo.labs.infobez_first.core.ports;

import itmo.labs.infobez_first.core.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> { }
