package com.example.moviedbtask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllMoviesAsAList() throws Exception {
        //GIVEN
        String expectedJSON = """
                [
                    {
                        "id": "1",
                        "name": "Harry Potter",
                        "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                        "publicationDate": "2001"
                    }
                ]
                """;
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }


    @Test
    void postOneMovieGetAListBack() throws Exception {
        //GIVEN
        String requestBody = """
            {
                "id": "1",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            """;

        String expectedJSON = """
           [
                {
                    "id": "0",
                    "name": "Harry Potter",
                    "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                    "publicationDate": "2001"
                }
            ]
            """;

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()) // hier die 200
                .andExpect(content().json(expectedJSON));
    }

    /*
    // Mocking
    MovieRepo movieRepo = mock(MovieRepo.class);
    when(movieRepo.addMovieToList(movie)).thenReturn(movie);

        movieRepo.addMovieToList(movie);
        movieRepo.addMovieToList(movie2);

    verify(MovieRepo);
 */

    @Test
    void deleteOneMovieGetAListBack() throws Exception {
        // Given
        String expectedJSON = """
           [
            ]
            """;

        String expectedJSON2 = """
            [
            {
                "id": "0",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            ]
            """;

        // Post is delete better here?
        String requestBody = """
            {
                "id": "1",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            """;

        mvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()) // hier die 200
                .andExpect(content().json(expectedJSON2));

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/movies/0"))
                .andExpect(status().isOk()) // hier die 200
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void getMovieReturnMovie() throws Exception {
        //GIVEN
        String expectedJSON = """
                    {
                        "id": "0",
                        "name": "Harry Potter",
                        "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                        "publicationDate": "2001"
                    }
                """;


        // post
        String requestBody = """
            {
                "id": "1",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            """;

        String expectedJSON2 = """
            [
            {
                "id": "0",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            ]
            """;

        mvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk()) // hier die 200
                .andExpect(content().json(expectedJSON2));

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/movies/0"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void putMovieReturnMovie() throws Exception {
        //GIVEN
        String expectedJSONForPut = """
                    {
                        "id": "0",
                        "name": "Harry Potter",
                        "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                        "publicationDate": "2001",
                        "favorit":true
                    }
                """;
        String requestBodyForPut = """
            {
                "favorit":true
            }
            """;

        // post
        String expectedJSONForPost = """
            [
            {
                "id": "0",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            ]
            """;

        String requestBodyForPost = """
            {
                "id": "1",
                "name": "Harry Potter",
                "url": "https://www.filmposter-archiv.de/filmplakat/2001/harry_potter_teaser2.jpg",
                "publicationDate": "2001"
            }
            """;

        mvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyForPost))
                .andExpect(status().isOk()) // hier die 200
                .andExpect(content().json(expectedJSONForPost));

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/movies/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyForPut))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSONForPut));
    }
}