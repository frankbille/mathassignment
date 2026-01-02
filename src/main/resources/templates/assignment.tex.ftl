\documentclass[12pt,a4paper,fleqn,leqno]{article}
\usepackage{amsmath}
\usepackage{cancel}
\usepackage[hidelinks]{hyperref}
\usepackage[a4paper,margin=2.5cm,includehead,includefoot]{geometry}
\usepackage[danish]{babel}
\usepackage{multicol}
\usepackage{fancyhdr}

\pagestyle{fancy}
\fancyhf{}
\fancyhead[L]{\leftmark}
\fancyhead[R]{\rightmark}
\fancyfoot[L]{Side \thepage}
\fancyfoot[R]{\tiny ${seed?c}}

\fancypagestyle{noheader}{
  \fancyhead{}
  \renewcommand{\headrulewidth}{0pt}
}

\setlength{\headheight}{14.5pt}
\setlength{\headsep}{20pt}

\begin{document}

\part*{Opgaver til løsning af ligninger}
\thispagestyle{noheader}

<#list sections as section>

\section*{${section.heading}}
\markboth{Opgaver til løsning af ligninger}{${section.heading}}

\begin{multicols*}{2}

<#list section.problems as problem>
\begin{equation}
  \tag{${section_index+1}.${problem_index+1}}\label{${section_index+1}.${problem_index+1}}
  ${problem.promptLatex}
\end{equation}
</#list>

\end{multicols*}

\newpage

</#list>

\part*{Opgave løsninger}
\thispagestyle{noheader}

<#list sections as section>

\section*{${section.heading}}
\markboth{Opgave løsninger}{${section.heading}}

\begin{multicols*}{2}
{
\setlength{\abovedisplayskip}{-12pt}
\setlength{\abovedisplayshortskip}{-12pt}
\setlength{\belowdisplayskip}{24pt}
\setlength{\belowdisplayshortskip}{24pt}

<#list section.problems as problem>
\begin{align}
  \tag{\ref{${section_index+1}.${problem_index+1}}}
  ${problem.answerLatex}
\end{align}
</#list>

}
\end{multicols*}

<#if section_has_next>\newpage</#if>

</#list>

\end{document}