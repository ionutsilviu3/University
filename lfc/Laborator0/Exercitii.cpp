#include <iostream>
#include <string>
#include <set>
#include <vector>
using namespace std;

//functie utilitara
bool esteAlfabet(string alfabet)
{
    set<char> setCaractere;
    for(char c : alfabet)
    setCaractere.insert(c);

    return alfabet.size() == setCaractere.size();

}

//exercitiul 1
int deCateOri(char sir[], char caracter)
{
    int nr = -1;
    for(int i = 0; i < sizeof(sir) - 1; i++)
    {
        if(sir[i] == caracter)
        nr++;
    }
    return nr;
}
//exercitiul 2
int esteIntregSauReal(string sir)
{
    bool intreg = true;
    bool nevalid = false;
    
    for(int i = 0; i < sir.length(); i++)
    {
        if(nevalid == false)
        {
                if(!isdigit(sir[i]) && sir[i] != '.')
                nevalid = true;
            if(sir[i] == '.' && i == 0)
                nevalid = true;

            if(sir[i] == '.' && intreg == true)
                intreg = false;
            else if(sir[i] == '.' && intreg == false)
                nevalid = true;
        }
            
        else
        {
            return -1;
        }
    }
    
    if(intreg == true)
    return 0;
    else
    return 1;

}
//exercitiul 3
bool esteCuvantPeste(string alfabet, string cuvant)
{
    if(esteAlfabet(alfabet))
        {
        
        for(char c : cuvant)
        {
            if(alfabet.find(c) == std::string::npos)
                return false;
        }
        }

    else
        return false;

        return true;
}
//exercitiul 4
string inlocuireSubsir(string cuvant, string subsir1, string subsir2)
{

    cuvant.replace(cuvant.find(subsir1),subsir1.length(), subsir2);
    return cuvant;
}

//exercitiul 5
vector<string> cuvinteDinFraza(string fraza, string delimitator)
{   
    
    vector<string> cuvinte;

    if(fraza.empty() == true)
    return cuvinte;

    size_t pos = 0;
    string cuvant;

        while ((pos = fraza.find(delimitator)) != std::string::npos) {
        cuvant = fraza.substr(0, pos);
        cuvinte.push_back(cuvant);
        fraza.erase(0, pos + delimitator.length());
        }
    cuvinte.push_back(fraza);
    cout<<"Sunt "<< + cuvinte.size()<<" cuvinte."<<endl;
    for(string c : cuvinte)
    cout<<c<<endl;
    
    return cuvinte;
}
int main()
{
    // char sir[] = "hallo";
    // char caracter = 'l';
    // cout<<deCateOri(sir,caracter)<<endl;

    // string sirNumere = "3.2";
    // cout<<esteIntregSauReal(sirNumere)<<endl;

    // string sirAlfabet = "abcdef";
    // string sirCuvant = "cbf";
    // cout<<esteCuvantPeste(sirAlfabet, sirCuvant)<<endl;

    // string sirCuvantSub = "hallowelt";
    // string subsir1 = "hallo";
    // string subsir2 = "hello";
    // cout<<inlocuireSubsir(sirCuvantSub,subsir1,subsir2)<<endl;
    string fraza = "Ana are mere";
    cuvinteDinFraza(fraza, " ");
    return 0;
}