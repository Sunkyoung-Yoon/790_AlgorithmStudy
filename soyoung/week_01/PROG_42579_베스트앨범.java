import java.util.*;

public class PROG_42579_베스트앨범 {

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // 장르별 총 재생 수 업데이트
            genrePlayCount.put(genre, genrePlayCount.getOrDefault(genre, 0) + play);

            // 장르별 노래 리스트 업데이트
            genreSongs.computeIfAbsent(genre, k -> new ArrayList<>()).add(new Song(i, play));
        }

        // 장르를 총 재생 수로 내림차순 정렬
        List<Map.Entry<String, Integer>> sortedGenres = new ArrayList<>(genrePlayCount.entrySet());
        sortedGenres.sort((o1, o2) -> o2.getValue() - o1.getValue());

        List<Integer> answer = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : sortedGenres) {
            String genre = entry.getKey();
            List<Song> songs = genreSongs.get(genre);
            songs.sort((s1, s2) -> s2.playCount - s1.playCount);
            answer.add(songs.get(0).id);
            if (songs.size() > 1) {
                answer.add(songs.get(1).id);
            }
        }

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }

    static class Song {
        int id;
        int playCount;

        Song(int id, int playCount) {
            this.id = id;
            this.playCount = playCount;
        }
    }
}
