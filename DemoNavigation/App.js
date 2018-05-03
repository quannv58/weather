/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  View
} from 'react-native';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import Header from './src/components/ui_component_demo/Header';
import ListAlbum from './src/components/ui_component_demo/ListAlbum';
import BaseView from './src/components/ui_component_demo/BaseView';
import reducers from './src/components/reducers';
import LibraryList from './src/components/ui_component_demo/LibraryList';
//import { Menu } from './src/components/navigation_demo/Router';

export default class App extends Component {
  render() {
    return (
      <Provider store={createStore(reducers)}>
        <View style={{ flex: 1 }}>
          <Header headerTitle='Tech Stack' />
            <ListAlbum />
          <BaseView />
        </View>
      </Provider>
    );
  }
}
