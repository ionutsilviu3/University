#include <iostream>
class Fractie
{
    
public:
int m_numitor;
int m_numarator;

Fractie(/* args */)
{}

Fractie(int numarator, int numitor)
{
    m_numitor = numitor;
    m_numarator = numarator;
}

~Fractie()
{
}
void set_numarator(int numarator)
{
    m_numarator = numarator;
}
int get_numarator()
{
    return m_numarator;
}
void set_numitor(int numitor)
{
    m_numitor = numitor;
}
int get_numitor()
{
    return m_numitor;
}
Fractie operator+ (const Fractie& fraction)
{   
    Fractie f;
    f.set_numarator(m_numarator + fraction.m_numarator);
    f.set_numitor(m_numitor + fraction.m_numitor);

    return f;
}
Fractie operator- (const Fractie& fraction)
{   
    Fractie f;
    f.set_numarator(m_numarator - fraction.m_numarator);
    f.set_numitor(m_numitor - fraction.m_numitor);

    return f;
}
Fractie operator* (const Fractie& fraction)
{   
    Fractie f;
    f.set_numarator(m_numarator * fraction.m_numarator);
    f.set_numitor(m_numitor * fraction.m_numitor);

    return f;
}
Fractie operator/ (const Fractie& fraction)
{   
    Fractie f;
    f.set_numarator(m_numarator * fraction.m_numitor);
    f.set_numitor(m_numitor * fraction.m_numarator);

    return f;
}


};

int main(int argc, char const *argv[])
{
    Fractie fractie(2,3);
    Fractie alta_fractie(3,6);
    alta_fractie = fractie / alta_fractie;

    std::cout<<alta_fractie.m_numarator << "\n" << "-" << "\n"<< alta_fractie.m_numitor<< std::endl;
    return 0;
}
