    package com.example.demo.model;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    @Setter
    @Getter
    @Entity
    @Table(name = "books", schema = "db")

    public class Book {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id" , nullable = false)
        private int id;


        @Column(name = "year")
        private int year;

        @Column(name = "language", length = 100)
        private String language;

        @Column(name = "title" )
        private String title;

        @Column(name = "page" )
        private int page;


        //constructo gol necesar pt jpa
        public Book() {

        }

        public Book(String newBook, int i, String ro, int i1) {
            this.title = newBook;
            this.year = i;
            this.language = ro;
            this.page = i1;
        }

        @Override
        public String   toString() {
            return "Book{" +
                    "id=" + id +
                    ", year=" + year +
                    ", language='" + language + '\'' +
                    ", title='" + title + '\'' +
                    ", page=" + page +
                    '}';
        }
    }
