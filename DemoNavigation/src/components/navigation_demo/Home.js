import React, { Component } from 'react';
import { 
    View,
    Text, 
    TouchableOpacity } from 'react-native';

export default class Home extends Component {
    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <Text>Home</Text>
                <TouchableOpacity 
                style={{ backgroundColor: 'green', marginTop: 20 }}
                onPress={() => { this.props.navigation.navigate('DetailScreen', { paramData: 'This data passed from home screen' }); }}
                >
                    <Text style={{ color: '#fff', fontSize: 20, padding: 10 }}>Go to detail</Text>
                </TouchableOpacity>
                <TouchableOpacity 
                style={{ backgroundColor: 'green', marginTop: 20 }}
                onPress={() => { this.props.navigation.navigate('DrawerOpen'); }}
                >
                    <Text style={{ color: '#fff', fontSize: 20, padding: 10 }}>Go to menu</Text>
                </TouchableOpacity>
            </View>
        );
    }
}
