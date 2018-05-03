import React, { StackNavigator } from 'react-navigation';
import TabNavigator from 'react-navigation/src/navigators/TabNavigator';
import DrawerNavigator from 'react-navigation/src/navigators/DrawerNavigator';
import Home from './Home';
import Detail from './Detail';
import User from './User';
import SideMenu from './SideMenu';

export const HomeStack = StackNavigator({
    HomeScreen: {
        screen: Home,
        navigationOptions: {
            title: 'Home Screen'
        }
    },

    DetailScreen: {
        screen: Detail,
        navigationOptions: {
            title: 'Detail Screen'
        }
    },
});

export const UserStack = StackNavigator({
    UserScreen: {
        screen: User,
        navigationOptions: {
            title: 'User Screen'
        }
    }
});

export const DetailStack = StackNavigator({
    DetailScreen: {
        screen: Detail,
        navigationOptions: {
            title: 'Detail Screen'
        }
    }
});

export const Tabbar = TabNavigator({
    Home: {
        screen: HomeStack,
        navigationOptions: {
            tabBarLabel: 'Tab Home'
        }
    },
    User: {
        screen: UserStack,
        navigationOptions: {
            tabBarLabel: 'Tab User'
        }
    }
},

{
    tabBarPosition: 'bottom',
    swipeEnable: true,
    tabBarOptions: {
        style: {
            backgroundColor: '#dddddd'
        },
        activeTintColor: 'red',
        inactiveTintColor: 'green'
    }
});

export const Menu = DrawerNavigator({
    Tabbar: {
        screen: Tabbar
    },
    Menu: {
        screen: SideMenu
    }

},
{
    drawerWidth: 300,
    drawerPosition: 'left',
    //contentComponent: props => <SideMenu {...props} />
});

