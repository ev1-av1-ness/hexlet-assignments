package exercise;

public class Article {
    private final long id;
    private final String title;
    private final String body;

    public Article(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public static class BuilderArticle {
        private long id;
        private String title;
        private String body;

        public BuilderArticle setId(long id) {
            this.id = id;
            return this;
        }

        public BuilderArticle setTitle(String title) {
            this.title = title;
            return this;
        }

        public BuilderArticle setBody(String body) {
            this.body = body;
            return this;
        }

        public Article build() {
            return new Article(id, title, body);
        }
    }
}
