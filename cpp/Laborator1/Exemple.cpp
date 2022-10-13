#include <iostream>

int f(int x) 
{
    return 2*x;
}
int r(int x,int y)
{
    return f(x) + y;
}
int main(int argc, char* argv[])
{
    std::cout<<"argc: "<<argc<<std::endl;
    for(int i = 0; i < argc; i++)
    {
        std::cout<<"argv "<< i << " is: " << argv[i]<<std::endl;
    }

    return 0;

}