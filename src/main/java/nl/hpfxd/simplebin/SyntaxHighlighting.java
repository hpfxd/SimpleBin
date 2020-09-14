package nl.hpfxd.simplebin;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Generated using:
 *
 * <code>
 * str = "";
 * i = 0;
 * CodeMirror.modeInfo.forEach(m => str += `${m.name.toUpperCase()}(${i++}, "${m.name}", "${m.mimes ? m.mimes[0] : m.mime}")\n`);
 * console.log(str);
 * </code>
 */
@Getter
public enum SyntaxHighlighting {
    APL(0, "APL", "text/apl"),
    PGP(1, "PGP", "application/pgp"),
    ASN_1(2, "ASN.1", "text/x-ttcn-asn"),
    ASTERISK(3, "Asterisk", "text/x-asterisk"),
    BRAINFUCK(4, "Brainfuck", "text/x-brainfuck"),
    C(5, "C", "text/x-csrc"),
    C_PLUS_PLUS(6, "C++", "text/x-c++src"),
    COBOL(7, "Cobol", "text/x-cobol"),
    C_SHARP(8, "C#", "text/x-csharp"),
    CLOJURE(9, "Clojure", "text/x-clojure"),
    CLOJURESCRIPT(10, "ClojureScript", "text/x-clojurescript"),
    CLOSURE_STYLESHEETS_GSS(11, "Closure Stylesheets (GSS)", "text/x-gss"),
    CMAKE(12, "CMake", "text/x-cmake"),
    COFFEESCRIPT(13, "CoffeeScript", "application/vnd.coffeescript"),
    COMMON_LISP(14, "Common Lisp", "text/x-common-lisp"),
    CYPHER(15, "Cypher", "application/x-cypher-query"),
    CYTHON(16, "Cython", "text/x-cython"),
    CRYSTAL(17, "Crystal", "text/x-crystal"),
    CSS(18, "CSS", "text/css"),
    CQL(19, "CQL", "text/x-cassandra"),
    D(20, "D", "text/x-d"),
    DART(21, "Dart", "application/dart"),
    DIFF(22, "diff", "text/x-diff"),
    DJANGO(23, "Django", "text/x-django"),
    DOCKERFILE(24, "Dockerfile", "text/x-dockerfile"),
    DTD(25, "DTD", "application/xml-dtd"),
    DYLAN(26, "Dylan", "text/x-dylan"),
    EBNF(27, "EBNF", "text/x-ebnf"),
    ECL(28, "ECL", "text/x-ecl"),
    EDN(29, "edn", "application/edn"),
    EIFFEL(30, "Eiffel", "text/x-eiffel"),
    ELM(31, "Elm", "text/x-elm"),
    EMBEDDED_JAVASCRIPT(32, "Embedded Javascript", "application/x-ejs"),
    EMBEDDED_RUBY(33, "Embedded Ruby", "application/x-erb"),
    ERLANG(34, "Erlang", "text/x-erlang"),
    ESPER(35, "Esper", "text/x-esper"),
    FACTOR(36, "Factor", "text/x-factor"),
    FCL(37, "FCL", "text/x-fcl"),
    FORTH(38, "Forth", "text/x-forth"),
    FORTRAN(39, "Fortran", "text/x-fortran"),
    F_SHARP(40, "F#", "text/x-fsharp"),
    GAS(41, "Gas", "text/x-gas"),
    GHERKIN(42, "Gherkin", "text/x-feature"),
    GITHUB_FLAVORED_MARKDOWN(43, "GitHub Flavored Markdown", "text/x-gfm"),
    GO(44, "Go", "text/x-go"),
    GROOVY(45, "Groovy", "text/x-groovy"),
    HAML(46, "HAML", "text/x-haml"),
    HASKELL(47, "Haskell", "text/x-haskell"),
    HASKELL_LITERATE(48, "Haskell (Literate)", "text/x-literate-haskell"),
    HAXE(49, "Haxe", "text/x-haxe"),
    HXML(50, "HXML", "text/x-hxml"),
    ASP_NET(51, "ASP.NET", "application/x-aspx"),
    HTML(52, "HTML", "text/html"),
    HTTP(53, "HTTP", "message/http"),
    IDL(54, "IDL", "text/x-idl"),
    PUG(55, "Pug", "text/x-pug"),
    JAVA(56, "Java", "text/x-java"),
    JAVA_SERVER_PAGES(57, "Java Server Pages", "application/x-jsp"),
    JAVASCRIPT(58, "JavaScript", "text/javascript"),
    JSON(59, "JSON", "application/json"),
    JSON_LD(60, "JSON-LD", "application/ld+json"),
    JSX(61, "JSX", "text/jsx"),
    JINJA2(62, "Jinja2", "text/jinja2"),
    JULIA(63, "Julia", "text/x-julia"),
    KOTLIN(64, "Kotlin", "text/x-kotlin"),
    LESS(65, "LESS", "text/x-less"),
    LIVESCRIPT(66, "LiveScript", "text/x-livescript"),
    LUA(67, "Lua", "text/x-lua"),
    MARKDOWN(68, "Markdown", "text/x-markdown"),
    MIRC(69, "mIRC", "text/mirc"),
    MARIADB_SQL(70, "MariaDB SQL", "text/x-mariadb"),
    MATHEMATICA(71, "Mathematica", "text/x-mathematica"),
    MODELICA(72, "Modelica", "text/x-modelica"),
    MUMPS(73, "MUMPS", "text/x-mumps"),
    MS_SQL(74, "MS SQL", "text/x-mssql"),
    MBOX(75, "mbox", "application/mbox"),
    MYSQL(76, "MySQL", "text/x-mysql"),
    NGINX(77, "Nginx", "text/x-nginx-conf"),
    NSIS(78, "NSIS", "text/x-nsis"),
    NTRIPLES(79, "NTriples", "application/n-triples"),
    OBJECTIVE_C(80, "Objective-C", "text/x-objectivec"),
    OBJECTIVE_C_PLUS_PLUS(81, "Objective-C++", "text/x-objectivec++"),
    OCAML(82, "OCaml", "text/x-ocaml"),
    OCTAVE(83, "Octave", "text/x-octave"),
    OZ(84, "Oz", "text/x-oz"),
    PASCAL(85, "Pascal", "text/x-pascal"),
    PEG_JS(86, "PEG.js", "null"),
    PERL(87, "Perl", "text/x-perl"),
    PHP(88, "PHP", "text/x-php"),
    PIG(89, "Pig", "text/x-pig"),
    PLAIN_TEXT(90, "Plain Text", "text/plain"),
    PLSQL(91, "PLSQL", "text/x-plsql"),
    POSTGRESQL(92, "PostgreSQL", "text/x-pgsql"),
    POWERSHELL(93, "PowerShell", "application/x-powershell"),
    PROPERTIES_FILES(94, "Properties files", "text/x-properties"),
    PROTOBUF(95, "ProtoBuf", "text/x-protobuf"),
    PYTHON(96, "Python", "text/x-python"),
    PUPPET(97, "Puppet", "text/x-puppet"),
    Q(98, "Q", "text/x-q"),
    R(99, "R", "text/x-rsrc"),
    RESTRUCTUREDTEXT(100, "reStructuredText", "text/x-rst"),
    RPM_CHANGES(101, "RPM Changes", "text/x-rpm-changes"),
    RPM_SPEC(102, "RPM Spec", "text/x-rpm-spec"),
    RUBY(103, "Ruby", "text/x-ruby"),
    RUST(104, "Rust", "text/x-rustsrc"),
    SAS(105, "SAS", "text/x-sas"),
    SASS(106, "Sass", "text/x-sass"),
    SCALA(107, "Scala", "text/x-scala"),
    SCHEME(108, "Scheme", "text/x-scheme"),
    SCSS(109, "SCSS", "text/x-scss"),
    SHELL(110, "Shell", "text/x-sh"),
    SIEVE(111, "Sieve", "application/sieve"),
    SLIM(112, "Slim", "text/x-slim"),
    SMALLTALK(113, "Smalltalk", "text/x-stsrc"),
    SMARTY(114, "Smarty", "text/x-smarty"),
    SOLR(115, "Solr", "text/x-solr"),
    SML(116, "SML", "text/x-sml"),
    SOY(117, "Soy", "text/x-soy"),
    SPARQL(118, "SPARQL", "application/sparql-query"),
    SPREADSHEET(119, "Spreadsheet", "text/x-spreadsheet"),
    SQL(120, "SQL", "text/x-sql"),
    SQLITE(121, "SQLite", "text/x-sqlite"),
    SQUIRREL(122, "Squirrel", "text/x-squirrel"),
    STYLUS(123, "Stylus", "text/x-styl"),
    SWIFT(124, "Swift", "text/x-swift"),
    STEX(125, "sTeX", "text/x-stex"),
    LATEX(126, "LaTeX", "text/x-latex"),
    SYSTEMVERILOG(127, "SystemVerilog", "text/x-systemverilog"),
    TCL(128, "Tcl", "text/x-tcl"),
    TEXTILE(129, "Textile", "text/x-textile"),
    TIDDLYWIKI(130, "TiddlyWiki", "text/x-tiddlywiki"),
    TIKI_WIKI(131, "Tiki wiki", "text/tiki"),
    TOML(132, "TOML", "text/x-toml"),
    TORNADO(133, "Tornado", "text/x-tornado"),
    TROFF(134, "troff", "text/troff"),
    TTCN(135, "TTCN", "text/x-ttcn"),
    TTCN_CFG(136, "TTCN_CFG", "text/x-ttcn-cfg"),
    TURTLE(137, "Turtle", "text/turtle"),
    TYPESCRIPT(138, "TypeScript", "application/typescript"),
    TYPESCRIPT_JSX(139, "TypeScript-JSX", "text/typescript-jsx"),
    TWIG(140, "Twig", "text/x-twig"),
    WEB_IDL(141, "Web IDL", "text/x-webidl"),
    VB_NET(142, "VB.NET", "text/x-vb"),
    VBSCRIPT(143, "VBScript", "text/vbscript"),
    VELOCITY(144, "Velocity", "text/velocity"),
    VERILOG(145, "Verilog", "text/x-verilog"),
    VHDL(146, "VHDL", "text/x-vhdl"),
    VUE_JS_COMPONENT(147, "Vue.js Component", "script/x-vue"),
    XML(148, "XML", "application/xml"),
    XQUERY(149, "XQuery", "application/xquery"),
    YACAS(150, "Yacas", "text/x-yacas"),
    YAML(151, "YAML", "text/x-yaml"),
    Z80(152, "Z80", "text/x-z80"),
    MSCGEN(153, "mscgen", "text/x-mscgen"),
    XU(154, "xu", "text/x-xu"),
    MSGENNY(155, "msgenny", "text/x-msgenny")
    ;

    private final int id;
    private final String name;
    private final String mime;

    SyntaxHighlighting(int id, String name, String mime) {
        this.id = id;
        this.name = name;
        this.mime = mime;
    }

    public static SyntaxHighlighting findFromId(int id) {
        return SyntaxCache.byId.get(id);
    }

    public static SyntaxHighlighting findFromName(String name) {
        return SyntaxCache.byName.get(name);
    }

    private static class SyntaxCache {
        private static final Map<Integer, SyntaxHighlighting> byId = new HashMap<>();
        private static final Map<String, SyntaxHighlighting> byName = new HashMap<>();

        static {
            for (SyntaxHighlighting value : SyntaxHighlighting.values()) {
                byId.put(value.getId(), value);
                byName.put(value.getName(), value);
            }
        }
    }
}
