<div id="top"></div>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<br />
<div align="center">

<h3 align="center">Tosan Log Tools</h3>

  <p align="center">
    toString builder and service logger utility
    <br />
  </p>
</div>


<!-- ABOUT THE PROJECT -->

## About The Project

This project try to solve two problems which are explained below:
<br/>

* In logging input or output of a service, object may contain sensitive data such as password, pan number, account
  balance, etc that can not show in plain format. some fields such as password should never be presented in log. some
  fields are sensitive but can be presented semi masked for debugging purposes.
  <br/>
  also you might want to present log in json format for later processing or might want it to be simple raw string for
  manual reading. implementing this requirement in every project leads to many code duplication and inconsistent
  formatting styles. its better you only state in toString method, how each field should be presented and leave the
  how's to other code. using this pattern leads to consistent log format. also changing the code is much easier because
  all the codes are in single place.
  <br/>
* For logging service calls, best practice is to do such things in aspects. in a log aspect, you first log which service
  called. then you log input provided to service. in the end you log the result of service or the thrown exception. you
  also may log part of result based on log level. in addition, you can log execution time of service. doing such can get
  more complex, as you must choose one style for logging objects and service call data. in other words, if you log
  objects in json format, for consistency must log service data in json too. its ideal you have integrated tool for
  logging service call, which take in mind all these considerations.

This java project, provides integrated solution for above mentioned problems. First it provides interface for use in
toString method and determine each field how should be shown. also, you can choose from two implementations provided for
simple and json formatting. also, provides interface for use in aspect to log service call.

## Getting Started

For start, you should add the following dependency to your pom:

 ```xml
  <groupId>com.tosan.tools</groupId>
<artifactId>tosan-tostring-builder</artifactId>
<version>${version}</version>
  ```

for use in toString method you can code like this:

```java
   @Override
public String toString() {
final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.semiEncryptedAppend("code", sMaskedCode);
        sb.append("name", name);
        sb.encryptedAppend("password", address);
        sb.append("tel", tel);
        sb.append("cityName", cityName);
        sb.leftEncryptedAppend("cityCode", LMaskedCityCode);
        return sb.toString();
        }
  ```
`ToStringBuilder` is an interface which provides multiple methods to determine how the field should be presented.
you can choose from three implementations:

* `ToStringBuilderImpl` this implementation based on static field `format` delegates formatting to
  `JsonToStringBuilderImpl` or `SimpleToStringBuilderImpl` implementation. default format for the field is json. you can
  set it to simple format by setter method before creating new instance.
* `JsonToStringBuilderImpl` this implementation formats object`s fields in json format.
* `SimpleToStringBuilderImpl` this implementation formats object`s fields in simple format.

<br/>
In log aspect, you can use `ServiceLogger` as simple as below:

```java
  @Aspect
public class LogAspect {
  private static final ServiceLogger serviceLogger = new ServiceLoggerImpl();

  @Around("com.tosan.modern.serviceCall()")
  public Object logFacadeCall(ProceedingJoinPoint pjp) throws Throwable {
    return serviceLogger.log(pjp);
  }
}
  ```

`ServiceLoggerImpl` this implementation based on `ToStringBuilderImpl.getFormat()` delegates formatting to
`JsonServiceLoggerImpl` or `SimpleServiceLoggerImpl` implementation. `serviceLogger.log(pjp)` method, hides all
the complexity of logging class name, called method, inputs, output, exception and execution time of called method.
<br/>
<p align="right">(<a href="#top">back to top</a>)</p>

### Prerequisites

This Library requires java version 8 or above.

<!-- CONTRIBUTING -->

## Contributing

Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->

## License

Distributed under the Apache License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>


[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge

[contributors-url]: https://github.com/Tosan/tosan-tostring-builder/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks//Best-README-Template.svg?style=for-the-badge

[forks-url]: https://github.com/Tosan/tosan-tostring-builder/network/members

[stars-shield]: https://img.shields.io/github/stars//Best-README-Template.svg?style=for-the-badge

[stars-url]: https://github.com/Tosan/tosan-tostring-builder/stargazers

[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge

[issues-url]: https://github.com/Tosan/tosan-tostring-builder/issues

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge

[license-url]: https://github.com/Tosan/tosan-tostring-builder/blob/master/LICENSE.txt

