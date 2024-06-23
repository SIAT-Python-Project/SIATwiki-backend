# SIAT WIKI Backend :yellow_heart:

<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">SIAT WIKI</h3>

  <p align="center">
    SIAT A반 사람들에 대해 알아보는 WIKI 프로젝드 입니다.
    <br />

</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#languages-libraries-and-tools-used">Languages, libraries and tools used</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#feature">Feature</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#coming-soon">Coming Soon</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
이 프로젝트는 SIAT A반 사람들이 각자의 위키를 만들어 자신의 Tech Stack이나 하고 싶은 Project 설명을 직접 작성할 수 있는 WIKI Backend Project 입니다.

<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>


### Languages, libraries and tools used
#### Languages
* JAVA
    - version: JDK-17

#### framework
* Spring Boot
    - version: 3.3.0
    - project: Maven

#### libraries
* SpringBoot data-jpa
    - version: 3.3.0
* SpringBoot starter-web
    - version: 3.3.0
* SpringBoot devtools
    - vsrsion: 3.3.0
* mysql-connector-j
    - version: 8.3.0
* lombok
    - version: 1.18.32
* SpringBoot starter-test
    - version: 3.3.0
* querydsl-apt
    - version: 5.1.0
* querydsl-jpa
    - version: 5.1.0

<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started
1.  Clone the repo
```sh
   git clone https://github.com/SIAT-Python-Project/SIATwiki-backend.git
  ```
2. Build Project
  ```sh
  cd ./SIATwiki-backend
  mvn -B package -DskipTests=true --file pom.xml
  ```

4. Run
  ```sh
  java -jar ./target/siatwiki-0.0.1-SNAPSHOT.jar
  ```
<!-- USAGE EXAMPLES -->
<!--Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources. -->


<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- FEATURE EXAMPLES -->
## Feature
1. Create User
  - use cookie auth
2. Create and Read Person
   - profile img upload
3. Write Person Info
   - Add Github Markdown style

<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap
- [x] User CRUD
- [x] Person CRUD
- [x] Info CRUD
- [x] Add Person profile image
- [x] Add Global Exception Handler
- [x] Cookie auth

See the [project issues](https://github.com/SIAT-Python-Project/SIATwiki-backend/issues) for a full list of proposed features(and known issues).

<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- COMING SOON -->

## Coming Soon
- [ ] Continuous Delivery
- [ ] Server Distribution

<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>

<!-- CONTACT -->
## Contact
hg_yellow
- GitHub: [hg_yellow](https://github.com/jang010505)
- Mail: hgyellow0505@gmail.com

배창민
- GitHub: [Changchang](https://github.com/bbmini96)
- Mail: changmin38@gmail.com

김지혜
- GitHub: [wisdom](https://github.com/Wisdom-Kim)
- Mail: cocoa389@naver.com


Project Link: [SIATwiki-backend](https://github.com/SIAT-Python-Project/SIATwiki-backend)<br/>
Project Link: [SIATwiki-frontend](https://github.com/SIAT-Python-Project/SIATwiki-frontend)<br/>
Project Team Link: [SIAT-Project](https://github.com/SIAT-Python-Project)
<p align="right">(<a href="#SIAT-WIKI-Backend-yellow_heart">back to top</a>)</p>
