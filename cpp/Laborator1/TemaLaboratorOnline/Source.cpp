#include <iostream>

class Phone{

    public:
    Phone()
    {};

    void call()
    {
        std::cout<<"Searching for signal...Calling.."<<std::endl;
    }

    bool hasKeys();
    bool hasInternet();

    private:
    int m_phone_number;
    int m_number_of_contacts;
};


class SmartPhone : public Phone{
    public:
    bool hasKeys()
    {
        return false;
    }
    bool hasInternet()
    {
        return true;
    }
};


class OldPhone : public Phone{
    public:
    bool hasKeys()
    {
        return true;
    }
    bool hasInternet()
    {
        return false;
    }
};

    int main(int argc, char const *argv[])
    {
        OldPhone old_phone;
        std::cout<<"Old Phone: ";
        old_phone.call();
        std::cout<<old_phone.hasInternet()<<std::endl;
        std::cout<<old_phone.hasKeys()<<std::endl;

        SmartPhone smart_phone;
        std::cout<<"Smart Phone: ";
        smart_phone.call();
        std::cout<<smart_phone.hasInternet()<<std::endl;
        std::cout<<smart_phone.hasKeys()<<std::endl;

        return 0;
    }
    

