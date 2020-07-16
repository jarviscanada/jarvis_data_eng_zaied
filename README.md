# Zaied Zaman . Jarvis Consulting

Self-motivated problem-solver and strong collaborator who always strives for innovative and most suitable solution. I have started working
as a Jarvis consultant after the completion of my Master's degree in Computer Science (Research-Based). Apart from my Master's research project,
I have collaborated with several labs to provide software solutions during my master's. These experiences grew interest and confidence inside
me to contribute to the data engineering industry. Practicing programming problem-solving and taking part in programming contests is one of
my hobbies alongside reading books on different topics.    

## Skills

**Proficient:** Algorithms & Data structures, Java, Linux/Bash, RDBMS/SQL, Agile/Scrum, Git, Springboot, Maven

**Competent:** C, C++, Image Processing algorithms e.g. BM3D, Image processing tools e.g. Matlab, OpenCV

**Familiar:** Python, JavaScript, Node.js, Express.js, HTML, CSS

## Development Projects

Project source code: [https://github.com/jarviscanada/jarvis_data_eng_zaied](https://github.com/jarviscanada/jarvis_data_eng_zaied)

- **[Cluster Monitor](./linux_sql)**: Developed a `Bash` and `PostgreSQL` based cluster monitoring agent for distributed systems. The Node machines send continuous hardware usage and hardware specification data which is monitored and persisted by server machine in a PostgreSQL database. The PostgreSQL database instance was hosted by a `docker` container. The container was created based on the PostgreSQL base image from the `docker registry`. `Maven` was
been used to manage dependencies.
- **[Core Java Apps](./core_java)**: Developed three `Maven` managed pure `Java`-based apps. 
    * The Twitter app shows, posts, deletes tweet data using the Twitter API. Efficient exception handling, logging, and testing frameworks were used in these apps. `self4j` facade and as the implementation `log4j` was used for the logging purpose whereas `Mockito` framework was used for unit testing and `Junit 4` was used for Integration testing. `MVC` architecture was used where `Dao` pattern was used for data access and retrieval. `Jackson` API was used to parse and create `JSON` objects to `POJO` objects or vice versa.
    * The JDBC app interacts with the `PostgreSQL` database with pure `Java` and JDBC driver. The `PostgreSQL` database instance is hosted by a docker container that was created from the `PostgreSQL` base image.
    * The grep app recursively traverses directories and subdirectories
and perform searching or text-processing
- **[SpringBoot App](./springboot)**: Developed this `maven` packaged `springboot` managed `Java` app. This is an `MVC` architecture driven `three-tier` `Microservice` REST API web app which can be consumed by any REST API client or browser. It responses back JSON object. It interacts with market data provider API, extracts stock Market data and shows, updates, deletes and additionally persists them in a dockerized `PostgreSQL` database. This app itself is also dockerized. `Springboot annotations` were used to facilitate the `IoC` container creation, bean creation, and `Dependency Injection`.
`Springboot` embedded `Tomcat` server serves as a web servlet API implementation to handle `HTTP` requests. `Repository` pattern was used as the data layer and `IEX API` was used as the market data provider API.`PostMan` and `Swagger` were used extensively to
test the app end-points. The decoupling of data and app makes it fault-tolerant.
- **[Cloud & DevOps](./cloud_devops)**: Not started
- **[Hadoop](./hadoop)**: Not started
- **[Spark/Scala](./spark)**:  Not started

## Professional Experiences

**Software Developer,  Jarvis, Toronto (April, 2020 - Present):** Primary Responsibility was to develop `Java`, `BASH/SQL`, `Springboot` based services and architectures utilizing `GitFlow` and other industry-recognized tools and best practices,  and collaborating with the team and other team members maintaining standard `agile` practices. Working in this role made me a better team player and gave me invaluable work environment experience to solve problems individually with minimal supervision.

**Research Assistant, Western University, London, ON (February, 2020 - March, 2020):** Primary responsibility was to develop a framework to integrate Quantum computing with Computer networking. Worked with `QCL` and `Python` to perform experiments. Finding scopes to utilize newer methods to existing problems improved my problem-solving ability.

**Research Assistant, Western University, London, ON (September, 2019 - January, 2020):** Mentored the current lab students and collaborated with labs to deliver an automation software solution. It was a medical imaging software that provides an efficient way to track and count blood cells from dyed brain images of rats. The solution was adaptable with different dyes and several image scenarios and distractions. The solution was written in `C++` and `iopng` library was used to read and write images to reduce latency. However, the solution was packaged as a `Mex` file to be invoked from `Matlab` for user convenience. This experience grew leadership and increased my collaboration abilities with clients.

**Research Assistant, Western University, London, ON (September, 2017 - August, 2019):** Designed and implemented an algorithm to improve image processing (denoising) performance which was my master's research topic. `BM3D` is the state-of-the-art algorithm for image denoising. My proposed algorithm works on top of `BM3D` and improves the overall denoising performance. The proposed algorithm beat the performance of `BM3D` significantly in all noise levels and varieties of images. The codebase was written in `C++`. To reduce the time complexity approaches like `Dynamic programming` was used while designing and low latency `C` libraries like `iopng` were used to read and write the image. Designing and implementing algorithms with minimal supervision made me confident to work individually.

**Teaching Assistant, Western University, London, ON (September, 2017 - August, 2019):** Designed and conducted labs, tutorials and assignments. Worked as a TA for `Introduction to Computer Organization and Architecture` course. Collaboration and mentoring are the primary improvements that I gained from this experience.

## Education & Academic Projects

**Western University (September, 2017- September, 2019)**, M.Sc. in Computer Science (Research-based)

- **Master's research algorithm:** My research field was Image Processing and Computer vision. Publication title was ["Optimizing the usage of
 2D and 3D transformations to improve the BM3D image denoising algorithm."](https://ir.lib.uwo.ca/etd/6523/) The implemented codebase was in C++. Optimization techniques like Dynamic 
 Programming was utilized to reduce time complexity. Exception handling and efficient memory usage were kept in mind during the implementation. 
 The efficient usage of several STL data structures had been done as well. Achieving this target gave me experience on how to manage a large codebase and add features compatible with it in a time and memory-efficient way.

**Islamic University of Technology (December, 2011- December, 2015)**, B.Sc. in Electrical and Electronic Engineering

- **MRI Imaging Software:** Published a paper on improving `Multiple Sclerosis (MS)` lesions recognition. Publication title was [“Inpainting multiple sclerosis lesions for improving registration performance with brain atlas” published in 2016 International Conference on Medical Engineering, Health Informatics and Technology (Meditec).](https://ieeexplore.ieee.org/document/7835363) White lesions are usually seen in the MRI images of `MS` patients and accurate differentiation is needed between actual lesion areas and other white areas which can occur for various reasons. The paper provides an efficient approach to solve the problem and later reconstruct the non-necessary regions. The implemented codebase was in `Matlab` and `C++`. Several open-source brain imaging tools and data formats have been used here. Collaborated with a remote team in this project. 

**Weather Forecasting API:** Implemented a weather forecasting API using the OpenWeather API data. Node.js and Express framework is used
in this app.

**Inventory System:** Implemented Inventory system and web page routing to render static and dynamic webpages. Node.js, HTML, CSS, and 
client-side javascript is used extensively. Integrated the native C++ code into the Node.js framework using N-API library.


## Certificates & Awards & Activities

- Western Graduate Research Scholarship (WGRS)
- Full free funding in Regular scheme in Islamic University of Technology
- Participated and qualified in 2019, 2020 Google Code Jam and 2019 Facebook Hacker Cup

