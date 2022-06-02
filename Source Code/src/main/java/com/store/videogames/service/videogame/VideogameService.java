package com.store.videogames.service.videogame;

import com.store.videogames.repository.entites.Videogame;
import com.store.videogames.repository.entites.enums.Platforms;
import com.store.videogames.repository.interfaces.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VideogameService implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Autowired
    VideogameRepository videogameRepository;

    @CachePut("Videogame")
    public void storeNewVideogame(Videogame videogame)
    {
        videogameRepository.save(videogame);
    }


    @Cacheable("Videogame")
    public Page<Videogame> retriveAllVideogames(int currentPage)
    {
        Pageable pageable = PageRequest.of(currentPage - 1, 25);
        return videogameRepository.findAll(pageable);
    }

    @Cacheable("Videogame")
    public Videogame getVideogameById(Integer videogameId)
    {
        return videogameRepository.getById(videogameId);
    }

    @Cacheable("Videogame")
    public Videogame getVideogameBygameName(String gameName)
    {
        return videogameRepository.getVideogameBygameName(gameName);
    }

    @Cacheable("Videogame")
    public List<Videogame> getVideogameByreleaseDate(LocalDate date)
    {
        return videogameRepository.getVideogameByreleaseDate(date);
    }

    @Cacheable("Videogame")
    public List<Videogame> getVideogameBypublisher(String publisher)
    {
        return videogameRepository.getVideogameBypublisher(publisher);
    }

    @Cacheable("Videogame")
    public List<Videogame> getVideogameBydeveloper(String developer)
    {
        return videogameRepository.getVideogameBydeveloper(developer);
    }

    @Cacheable("Videogame")
    public List<Videogame> searchforVideogame(String keyword)
    {
        return videogameRepository.videogamesSearch(keyword);
    }

    @Cacheable("Videogame")
    public Page<Videogame> getVideogamesByPlatform(Platforms platform, int currentPage)
    {
        Pageable pageable = PageRequest.of(currentPage - 1, 5);
        return videogameRepository.allGamesofAplatform(platform, pageable);
    }
}
